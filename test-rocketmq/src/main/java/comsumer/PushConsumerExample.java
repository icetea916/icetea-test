package comsumer;

import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * push消费者
 * https://rocketmq.apache.org/zh/docs/featureBehavior/06consumertype/#pushconsumer
 */
public class PushConsumerExample {
    private static final Logger logger = LoggerFactory.getLogger(PushConsumerExample.class);

    /**
     * 推模式消费消息
     */
    public static void main(String[] args) throws ClientException, IOException, InterruptedException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        // 接入点地址，需要设置成Proxy的地址和端口列表，一般是xxx:8081;xxx:8081。
        String endpoints = "127.0.0.1:8081";
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
                .setEndpoints(endpoints)
                .build();
        // 订阅消息的过滤规则，表示订阅所有Tag的消息。
        FilterExpression filterExpression = new FilterExpression("*", FilterExpressionType.TAG);
        // 指定需要订阅哪个目标Topic，Topic需要提前创建。
        String topic = "test_topic";
        Map<String, FilterExpression> map = new HashMap<>();
        map.put(topic, filterExpression);

        // 初始化PushConsumer，需要绑定消费者分组ConsumerGroup、通信参数以及订阅关系。
        PushConsumer pushConsumer = provider.newPushConsumerBuilder()
                .setClientConfiguration(clientConfiguration)
                // 设置消费者分组
                .setConsumerGroup("test_consumer_group")
                // 设置预绑定的订阅关系。key=topic, value=FilterExpression。
                .setSubscriptionExpressions(map)
                .setConsumptionThreadCount(1)
                .setMaxCacheMessageCount(1000)
                // 设置消费监听器
                .setMessageListener(messageView -> {
                    // 处理消息并返回消费结果。
                    String mb = StandardCharsets.UTF_8.decode(messageView.getBody()).toString();
                    logger.info("Consume message successfully, messageId={}, mb={}", messageView.getMessageId(), mb);
                    return ConsumeResult.SUCCESS;
                }).build();

        // 如果不需要再使用 PushConsumer，可关闭该实例。
//        pushConsumer.close();
    }

}