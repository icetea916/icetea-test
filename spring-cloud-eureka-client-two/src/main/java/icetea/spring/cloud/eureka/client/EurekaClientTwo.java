package icetea.spring.cloud.eureka.client;

import icetea.util.config.UserContextConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@Import(UserContextConfig.class)
public class EurekaClientTwo {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientTwo.class);
    }
}
