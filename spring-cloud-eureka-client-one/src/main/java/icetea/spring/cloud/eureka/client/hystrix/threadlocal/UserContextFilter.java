package icetea.spring.cloud.eureka.client.hystrix.threadlocal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest
            , ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        UserContext context = UserContextHolder.getContext();

        context.setCorrelationId(request.getHeader(UserContext.CORRELATIKON_ID));

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
