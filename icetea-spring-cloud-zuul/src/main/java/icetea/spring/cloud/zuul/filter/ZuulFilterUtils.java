package icetea.spring.cloud.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import icetea.util.user.UserContext;
import org.springframework.stereotype.Component;

@Component
public class ZuulFilterUtils {

    /**
     * zuul过滤器类型
     */
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";

    public static String getCorrelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(UserContext.CORRELATION_ID) != null) {
            return ctx.getRequest().getHeader(UserContext.CORRELATION_ID);
        } else {
            return ctx.getZuulRequestHeaders().get(UserContext.CORRELATION_ID);
        }
    }

    public static void setCorrelationId(String correlationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(UserContext.CORRELATION_ID, correlationId);
    }

}
