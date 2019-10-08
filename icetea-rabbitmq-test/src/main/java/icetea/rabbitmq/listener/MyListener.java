package icetea.rabbitmq.listener;

import icetea.rabbitmq.constant.RabbitMqConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@RabbitListener(queues = {RabbitMqConstant.QUEUE_ICETEA_TEST_1}, containerFactory = "rabbitListenerContainerFactory", concurrency = "5-10")
@Component
public class MyListener {

    @RabbitHandler
    public void handlerMessage(Message message, Map<String, Object> map) {
        new Jackson2JsonMessageConverter();
        System.out.println(new String(message.getBody()));
        System.out.println(message.getMessageProperties().toString());
        System.out.println(map.toString());
    }
}
