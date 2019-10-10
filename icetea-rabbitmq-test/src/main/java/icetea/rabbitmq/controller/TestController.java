package icetea.rabbitmq.controller;

import icetea.rabbitmq.constant.RabbitMqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("test/{message}")
    public String testController(@PathVariable("message") String message) {
        Map<String, String> map = new HashMap<>();
        map.put("username", "icetea");
        map.put("password", "icetea/password");
        map.put("message", message);
        rabbitTemplate.convertAndSend(RabbitMqConstant.QUEUE_ICETEA_TEST_1, map);

        return "ok";
    }
}
