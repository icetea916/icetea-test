package icetea.test;

import icetea.test.rabbitmq.RabbitMQApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@SpringBootTest(classes = RabbitMQApplication.class)
@RunWith(SpringRunner.class)
public class MyProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 简单送消息
     */
    @Test
    public void simpleProduce() {
        Message message = MessageBuilder.withBody("hello world".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setHeader("bar", "baz")
                .build();
        // 发送简单信息
        rabbitTemplate.send("testQueue", message);
    }

    /**
     * 使用converter发送消息
     */
    @Test
    public void converterProduce() {
        // 发送简单信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "icetea");

        rabbitTemplate.convertAndSend("testQueue", map);
    }

}
