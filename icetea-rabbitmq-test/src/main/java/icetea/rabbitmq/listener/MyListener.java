package icetea.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import icetea.rabbitmq.constant.RabbitMqConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@RabbitListener(queues = {RabbitMqConstant.QUEUE_ICETEA_TEST_1},
        containerFactory = "rabbitListenerContainerFactory",
        concurrency = "5-10")
@Component
public class MyListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);

    @RabbitHandler
    public void handlerMessage(Map<String, Object> map, Channel channel, Message message) throws IOException {
        LOGGER.info("message={}", map);
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println("=================消息头====================");
        System.out.println("messageProperties=" + messageProperties);
        System.out.println("messageId=" + messageProperties.getMessageId());
        System.out.println("receivedExchange=" + messageProperties.getReceivedExchange());
        System.out.println("consumerQueue=" + messageProperties.getConsumerQueue());
        System.out.println("deliveryTag= " + messageProperties.getDeliveryTag());

        String msg = (String) map.get("message");
        if ("error".equals(msg)) {
            throw new RuntimeException("测试异常重试机制");
        }

        channel.basicAck(messageProperties.getDeliveryTag(), false);
    }
}
