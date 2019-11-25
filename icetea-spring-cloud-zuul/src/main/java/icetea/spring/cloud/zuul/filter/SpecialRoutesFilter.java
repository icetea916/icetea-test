package icetea.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 路由过滤器：可以转发路由
 */
@Component
public class SpecialRoutesFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(SpecialRoutesFilter.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProxyRequestHelper helper = new ProxyRequestHelper();


    @Override
    public String filterType() {
        return ZuulFilterUtils.ROUTE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        // might not have a service id if we are using a static, non-eureka route.
        String serviceId = ctx.get("serviceId") == null ? "" : ctx.get("serviceId").toString();

        // 执行restTemplate确定是否有id路由记录
        AbTestingRoute abRoutingInfo = getAbRoutingInfo(serviceId);
        if (abRoutingInfo != null && useSpecialRoute(abRoutingInfo)) {
            // 构造转发路径
            int index = ctx.getRequest().getRequestURI().indexOf(serviceId);
            String strippedRoute = ctx.getRequest().getRequestURI().substring(index + serviceId.length());
            String route = String.format("%s/%s", abRoutingInfo.getEndpoint(), strippedRoute);
            logger.debug("构造的转发路由为：{}", route);

            // 进行转发
            RequestContext context = RequestContext.getCurrentContext();
            HttpServletRequest request = context.getRequest();

            MultiValueMap<String, String> headers = this.helper
                    .buildZuulRequestHeaders(request);
            MultiValueMap<String, String> params = this.helper
                    .buildZuulRequestQueryParams(request);
            String verb = request.getMethod().toUpperCase();

            InputStream requestEntity = null;
            try {
                requestEntity = request.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (request.getContentLength() < 0) {
                context.setChunkedRequestBody();
            }

            this.helper.addIgnoredHeaders();
            CloseableHttpClient httpClient = null;
            HttpResponse response = null;

            try {
                httpClient = HttpClients.createDefault();
                response = forward(httpClient, verb, route, request, headers,
                        params, requestEntity);
                setResponse(response);
            } catch (Exception ex) {
                ex.printStackTrace();

            } finally {
                try {
                    httpClient.close();
                } catch (IOException ex) {
                }
            }
        }

        return null;
    }

    private void setResponse(HttpResponse response) throws IOException {
        this.helper.setResponse(response.getStatusLine().getStatusCode(),
                response.getEntity() == null ? null : response.getEntity().getContent(),
                revertHeaders(response.getAllHeaders()));
    }

    private MultiValueMap<String, String> revertHeaders(Header[] headers) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        for (Header header : headers) {
            String name = header.getName();
            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<String>());
            }
            map.get(name).add(header.getValue());
        }
        return map;
    }

    private HttpResponse forward(HttpClient httpclient, String verb, String uri,
                                 HttpServletRequest request, MultiValueMap<String, String> headers,
                                 MultiValueMap<String, String> params, InputStream requestEntity)
            throws Exception {
        Map<String, Object> info = this.helper.debug(verb, uri, headers, params,
                requestEntity);
        URL host = new URL(uri);
        HttpHost httpHost = getHttpHost(host);

        HttpRequest httpRequest;
        int contentLength = request.getContentLength();
        InputStreamEntity entity = new InputStreamEntity(requestEntity, contentLength,
                request.getContentType() != null
                        ? ContentType.create(request.getContentType()) : null);
        switch (verb.toUpperCase()) {
            case "POST":
                HttpPost httpPost = new HttpPost(uri);
                httpRequest = httpPost;
                httpPost.setEntity(entity);
                break;
            case "PUT":
                HttpPut httpPut = new HttpPut(uri);
                httpRequest = httpPut;
                httpPut.setEntity(entity);
                break;
            case "PATCH":
                HttpPatch httpPatch = new HttpPatch(uri);
                httpRequest = httpPatch;
                httpPatch.setEntity(entity);
                break;
            default:
                httpRequest = new BasicHttpRequest(verb, uri);

        }
        try {
            httpRequest.setHeaders(convertHeaders(headers));
            HttpResponse zuulResponse = forwardRequest(httpclient, httpHost, httpRequest);

            return zuulResponse;
        } finally {
        }
    }

    private HttpResponse forwardRequest(HttpClient httpclient, HttpHost httpHost,
                                        HttpRequest httpRequest) throws IOException {
        return httpclient.execute(httpHost, httpRequest);
    }

    private Header[] convertHeaders(MultiValueMap<String, String> headers) {
        List<Header> list = new ArrayList<>();
        for (String name : headers.keySet()) {
            for (String value : headers.get(name)) {
                list.add(new BasicHeader(name, value));
            }
        }
        return list.toArray(new BasicHeader[0]);
    }

    private HttpHost getHttpHost(URL host) {
        HttpHost httpHost = new HttpHost(host.getHost(), host.getPort(),
                host.getProtocol());
        return httpHost;
    }

    private AbTestingRoute getAbRoutingInfo(String serviceName) {
        ResponseEntity<AbTestingRoute> restExchange = null;
        try {
            restExchange = restTemplate.exchange(
                    "http://client-one/route-filter",
                    HttpMethod.GET,
                    null, AbTestingRoute.class, serviceName);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) return null;
            throw ex;
        }

        return restExchange.getBody();
    }

    /**
     * 判断是否使用替代路由
     *
     * @return
     */
    private boolean useSpecialRoute(AbTestingRoute abTestingRoute) {
        Random random = new Random();

        if (abTestingRoute.active.equals("N")) {
            return false;
        }

        int value = random.nextInt(10) + 1;

        if (abTestingRoute.getWeight() < value) {
            return false;
        }

        return false;
    }

}
