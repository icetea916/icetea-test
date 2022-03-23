package life.icetea.test.springmvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * ip拦截器
 */
@Slf4j
//@Component
public class IpFilterInterceptor extends HandlerInterceptorAdapter {

    @Value("#{'${order.platform.allow.ip.list}'.split(',')}")
    private List<String> ipList;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String ip = request.getHeader("X-Real-IP");
        log.debug(" ip is : {}, requestUri:{}", ip, request.getRequestURI());
        if (ip != null && !ipList.contains(ip)) {
            throw new Exception("ip is invalid");
        }

        return super.preHandle(request, response, handler);
    }
}
