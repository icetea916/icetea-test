package icetea.rabbitmq.test;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new CustomMessageConvert();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String uri = URLEncoder.encode("http://39.105.37.91:8770/actuator/hystrix.stream", "UTF-8");
        System.out.println(uri);

        new StringBuilder("forward:/hystrix/monitor?").append("stream=").append(uri).append("title=class");
    }
}
