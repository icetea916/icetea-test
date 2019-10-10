package icetea.rabbitmq.config;

import icetea.rabbitmq.constant.RabbitMqConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitMQConfig {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 声明自定义的topic交换机
     */
    @Bean("topicExchange")
    public Exchange topicExchange() {
        return new TopicExchange(RabbitMqConstant.EXCHANGE_ICETEA_TOPIC);
    }

    /**
     * 声明自定义的direct交换机
     */
    @Bean("directExchange")
    public Exchange directExchange() {
        return new DirectExchange(RabbitMqConstant.EXCHANGE_ICETEA_DIRECT);
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MessageRecoverer messageRecoverer() {
        return new RepublishMessageRecoverer(amqpTemplate, RabbitMqConstant.EXCHANGE_ICETEA_DIRECT, RabbitMqConstant.QUEUE_DLX_ICETEA_TEST1);
    }

}
