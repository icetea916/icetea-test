package icetea.spring.cloud.zuul;

import icetea.spring.cloud.zuul.filter.FilterUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// 若想使用eureka必须导入spring Cloud的eureka包
@EnableZuulProxy
public class MyZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyZuulApplication.class);
    }

    @Bean
    public FilterUtils getFilterUtils() {
        return new FilterUtils();
    }
}
