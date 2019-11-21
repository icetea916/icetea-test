package icetea.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TrackingFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    @Autowired
    FilterUtils filterUtils;

    /**
     * 该方法用于告诉zuul该过滤器是前置过滤器、路由过滤器、还是后置过滤器
     */
    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    /**
     * 返回一个整数，表示不同类型的过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean表示该过滤器是否要执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 没次通过过滤器所执行的方法
     */
    @Override
    public Object run() throws ZuulException {
        if (filterUtils.getCorrelationId() != null) {
            logger.debug("tmx-correlation-id={}.", filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(UUID.randomUUID().toString());
            logger.debug("tmx-correlation-id={}.", filterUtils.getCorrelationId());
        }
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.debug("处理了请求：{}", ctx.getRequest().getRequestURI());

        return null;
    }
}
