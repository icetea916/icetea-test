package comsumer;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.SimpleConsumer;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

/**
 * @author icetea
 * @date 2024/4/30
 */
public class SimpleConsumerExample {

    public static final Logger logger = LoggerFactory.getLogger(SimpleConsumerExample.class);

    public static void main(String[] args) throws ClientException {
        // 消费示例：使用 SimpleConsumer 消费普通消息，主动获取消息处理并提交。
        String topic = "test_topic";
        FilterExpression filterExpression = new FilterExpression("*", FilterExpressionType.TAG);
        SimpleConsumer simpleConsumer = ClientServiceProvider.loadService().newSimpleConsumerBuilder()
                // 设置消费者分组。
                .setConsumerGroup("test_simple_consumer_group")
                // 设置接入点。
                .setClientConfiguration(ClientConfiguration.newBuilder().setEndpoints("127.0.0.1:8081").build())
                // 设置预绑定的订阅关系。
                .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                // 设置从服务端接受消息的最大等待时间
                .setAwaitDuration(Duration.ofSeconds(10))
                .build();
        try {
            // SimpleConsumer 需要主动获取消息，并处理。
            List<MessageView> messageViewList = simpleConsumer.receive(100, Duration.ofSeconds(30));
            messageViewList.forEach(messageView -> {
                System.out.println(messageView);
                // 消费处理完成后，需要主动调用 ACK 提交消费结果。
                try {
                    simpleConsumer.ack(messageView);
                } catch (ClientException e) {
                    logger.error("Failed to ack message, messageId={}", messageView.getMessageId(), e);
                }
            });
        } catch (ClientException e) {
            // 如果遇到系统流控等原因造成拉取失败，需要重新发起获取消息请求。
            logger.error("Failed to receive message", e);
        }
    }

}
