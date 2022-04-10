package life.icetea.test.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;


public class MyFeignConfiguration {

    /**
     * 实现对feign的请求进行拦截的拦截器
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new MyRequestInterceptor();
    }

    /**
     * feign请求日志全局配置， 也可在配置文件中配置如：feign.client.config.loggerLevel: full
     * <p>
     * 结合spirng loggin使用配置了debug级别才会显示
     */
//    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}