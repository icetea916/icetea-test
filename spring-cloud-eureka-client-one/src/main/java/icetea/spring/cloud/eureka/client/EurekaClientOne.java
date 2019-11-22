package icetea.spring.cloud.eureka.client;

import icetea.util.config.UserContextConfig;
import icetea.util.user.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
@Import(UserContextConfig.class)
public class EurekaClientOne {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientOne.class);
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 获取拦截器列表
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        // 添加自定义UserContextInterceptor
        interceptors.add(new UserContextInterceptor());

        return restTemplate;
    }
}
