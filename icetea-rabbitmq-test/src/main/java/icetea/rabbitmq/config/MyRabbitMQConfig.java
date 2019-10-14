package icetea.rabbitmq.config;

import icetea.rabbitmq.constant.RabbitMqConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(QueueConfig.class)
public class MyRabbitMQConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyRabbitMQConfig.class);

    /**
     * 声明自定义的topic交换机
     */
    @Bean("topicExchange")
    public Exchange topicExchange() {
        LOGGER.info("声明topic交换器{}", RabbitMqConstant.EXCHANGE_ICETEA_TOPIC);
        return new TopicExchange(RabbitMqConstant.EXCHANGE_ICETEA_TOPIC);
    }

    /**
     * 声明自定义的direct交换机
     */
    @Bean("directExchange")
    public Exchange directExchange() {
        LOGGER.info("声明direct交换器{}", RabbitMqConstant.EXCHANGE_ICETEA_DIRECT);
        return new DirectExchange(RabbitMqConstant.EXCHANGE_ICETEA_DIRECT);
    }

    @Bean("dlx")
    public DirectExchange dlx() {
        LOGGER.info("声明direct交换器{},用于路由死信队列", RabbitMqConstant.EXCHANGE_COMMON_DLX);
        return new DirectExchange(RabbitMqConstant.EXCHANGE_COMMON_DLX, true, false);
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public MessageRecoverer messageRecoverer() {
//        return new RepublishMessageRecoverer(amqpTemplate, RabbitMqConstant.EXCHANGE_ICETEA_DIRECT, RabbitMqConstant.QUEUE_DLX_ICETEA_TEST1);
//    }

}
