package icetea.util.user;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 该拦截器需要在restTemplate配置
 */
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    /**
     * 该方法在restTemplate发生实际的HTTP服务调用之前被调用
     *
     * @param httpRequest
     * @param bytes
     * @param clientHttpRequestExecution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        // 为远程的调用request首部添加关联的唯一id
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());


        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

}
