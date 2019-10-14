package icetea.rabbitmq.test;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class TestConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new CustomMessageConvert();
    }
}
