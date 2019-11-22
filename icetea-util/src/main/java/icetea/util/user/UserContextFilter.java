package icetea.util.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserContextFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UserContext context = UserContextHolder.getContext();
        // 设置唯一id
        context.setCorrelationId(request.getHeader(UserContext.CORRELATION_ID));

        filterChain.doFilter(request, servletResponse);
    }

}

