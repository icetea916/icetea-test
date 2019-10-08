package icetea.rabbitmq.config;

import icetea.rabbitmq.constant.RabbitMqConstant;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class MyRabbitMQConfig {
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

    @Bean("rabbitListenerContainerFactory")
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}
