package icetea.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import icetea.util.user.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 后置过滤器，用于将关联id注入到response请求头中
 */
@Component
public class ResponseFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    public String filterType() {
        return ZuulFilterUtils.POST_FILTER_TYPE;
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
        // 获取原始的请求并将其注入到响应头中
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.debug("将correlationId注入当response头中：correlationId={}", ZuulFilterUtils.getCorrelationId());
        ctx.getResponse().addHeader(UserContext.CORRELATION_ID, ZuulFilterUtils.getCorrelationId());

        return null;
    }

}
