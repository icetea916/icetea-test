package icetea.rabbitmq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import icetea.rabbitmq.constant.RabbitMqConstant;
import icetea.util.amqp.AmqpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

@RabbitListener(queues = {RabbitMqConstant.QUEUE_ICETEA_TEST_1},
        containerFactory = "rabbitListenerContainerFactory",
        concurrency = "5-10")
//@Component
public class MyListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);
    @Autowired
    private AmqpUtils amqpUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitHandler
    public void handlerMessage(Map<String, Object> map, Message message) {
        amqpUtils.handleMessageWithRetry(message, RabbitMqConstant.QUEUE_DLX_ICETEA_TEST1, this::handleMessage);
    }


    public void handleMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println("=================消息头====================");
        System.out.println("messageProperties=" + messageProperties);
        System.out.println("messageId=" + messageProperties.getMessageId());
        System.out.println("receivedExchange=" + messageProperties.getReceivedExchange());
        System.out.println("consumerQueue=" + messageProperties.getConsumerQueue());
        System.out.println("deliveryTag= " + messageProperties.getDeliveryTag());

        String msgStr = new String(message.getBody(), Charset.defaultCharset());
        Map map = null;
        try {
            map = objectMapper.readValue(msgStr, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("反序列化失败");
        }

        if (map == null || "error".equals(map.get("message"))) {
            throw new RuntimeException("测试异常重试机制");
        }
    }
}
