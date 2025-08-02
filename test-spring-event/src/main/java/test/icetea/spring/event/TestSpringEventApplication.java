package test.icetea.spring.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TestSpringEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringEventApplication.class, args);
    }

}