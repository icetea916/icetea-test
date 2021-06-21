package life.icetea.test.springmvc.usercontext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class UserContextConfig {
    private static Logger logger = LoggerFactory.getLogger(UserContextConfig.class);


    /**
     * 注入userContext的servlet过滤器，方便获取请求头中的信息
     *
     * @return
     */
    @Bean
    public UserContextFilter userContextFilter() {
        logger.debug("加载UserContext上下文过滤器：UserContextFilter ......");
        return new UserContextFilter();
    }

    /**
     * 注入userContext的httpClient请求拦截器，用于服务之间调用往http请求头添加correlationId
     *
     * @return
     */
    @Bean
    public UserContextInterceptor userContextInterceptor() {
        logger.debug("加载UserContext服务调用httpClient拦截器：UserContextInterceptor ......");
        return new UserContextInterceptor();
    }
}
