package icetea.test.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文档:
 * https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/boot-features-messaging.html#boot-features-amqp
 * https://docs.spring.io/spring-amqp/docs/2.2.16.RELEASE/reference/html/
 */
@SpringBootApplication
public class RabbitMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class);
    }

}
