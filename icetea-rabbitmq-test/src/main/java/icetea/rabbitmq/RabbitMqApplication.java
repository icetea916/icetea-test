package icetea.rabbitmq;

import icetea.rabbitmq.config.MyRabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Import(MyRabbitMQConfig.class)
@EnableRabbit
public class RabbitMqApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "icetea");
        map.put("password", "icetea/password");
        rabbitTemplate.convertAndSend("icetea.test1", map);
    }
}
