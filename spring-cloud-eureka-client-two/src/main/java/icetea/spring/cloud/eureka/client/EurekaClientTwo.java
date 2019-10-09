package icetea.spring.cloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientTwo {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientTwo.class);
    }
}
