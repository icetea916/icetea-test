package icetea.rabbitmq;

import icetea.rabbitmq.config.MyRabbitMQConfig;
import icetea.util.amqp.AmqpUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MyRabbitMQConfig.class)
@EnableRabbit
public class RabbitMqApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class);
    }

    @Bean
    public AmqpUtils amqpUtils(AmqpTemplate amqpTemplate) {
        return new AmqpUtils(amqpTemplate);
    }

    @Override
    public void run(String... args) throws Exception {
//        Map<String, String> map = new HashMap<>();
//        map.put("username", "icetea");
//        map.put("password", "icetea/password");
//        rabbitTemplate.convertAndSend("icetea.test1", map);
    }
}
