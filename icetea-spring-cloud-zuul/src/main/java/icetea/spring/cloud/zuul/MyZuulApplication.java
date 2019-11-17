package icetea.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
// 若想使用eureka必须导入spring Cloud的eureka包
@EnableZuulProxy
public class MyZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyZuulApplication.class);
    }
}
