package icetea.test.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyConsumer {

    /**
     * simple 消费者
     */
    @SneakyThrows
    @RabbitListener(queues = {"testQueue"})
    public void simpleConsumerMessage(Message message, Channel channel) {
        MessageProperties messageProperties = message.getMessageProperties();
        log.info("messageProperties={}", messageProperties);
        log.info("messageBody={}", message.getBody());
        log.info("消费消息={}", new String(message.getBody()));
        // spring.rabbitmq.listener.simple.acknowledge-mode 设置成了手动需要使用channel进行手动确认
        channel.basicAck(messageProperties.getDeliveryTag(), false);
    }

}
