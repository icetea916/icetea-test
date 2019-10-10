package icetea.rabbitmq.config;

import icetea.rabbitmq.constant.RabbitMqConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;


    /**
     * 声明测试队列
     *
     * @param exchange
     */
    @Autowired
    public void setMyIceteaTest1(@Qualifier("directExchange") Exchange exchange) {
        // 先声明死信队列
        Queue dlxQueue = new Queue(RabbitMqConstant.QUEUE_DLX_ICETEA_TEST1);
        amqpAdmin.declareQueue(dlxQueue);
        Binding dlxQueueBinding = BindingBuilder.bind(dlxQueue).to(exchange).with(RabbitMqConstant.QUEUE_DLX_ICETEA_TEST1).noargs();
        amqpAdmin.declareBinding(dlxQueueBinding);
        // 构造声明队列参数
        Map<String, Object> argsMap = new HashMap<>();
        argsMap.put("x-dead-letter-exchange", RabbitMqConstant.EXCHANGE_ICETEA_DIRECT);
        argsMap.put("x-dead-letter-routing-key", RabbitMqConstant.QUEUE_DLX_ICETEA_TEST1);
        Queue queue = new Queue(RabbitMqConstant.QUEUE_ICETEA_TEST_1, true, false, false, argsMap);
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue)
                .to(exchange)
                .with(RabbitMqConstant.QUEUE_ICETEA_TEST_1)
                .noargs();
        amqpAdmin.declareBinding(binding);
    }


    @Autowired
    public void setMyIceteaTest2(@Qualifier("topicExchange") Exchange exchange) {
        Queue queue = new Queue(RabbitMqConstant.QUEUE_ICETEA_TEST_2);
        amqpAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue)
                .to(exchange)
                .with(RabbitMqConstant.QUEUE_ICETEA_TEST_2)
                .noargs();
        amqpAdmin.declareBinding(binding);
    }
}
