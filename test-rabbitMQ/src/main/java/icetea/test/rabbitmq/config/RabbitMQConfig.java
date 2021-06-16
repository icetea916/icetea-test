package icetea.test.rabbitmq.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * RabbitAdmin 类 的底层实现就是从 Spring 容器中获取 exchange、Bingding、routingkey
     * 以及queue 的 @bean 声明然后使用 rabbitTemplate 的 execute 方法进行执行对应的声明、
     * 修改、删除等一系列 rabbitMQ 基础功能操作。例如添加交换机、删除一个绑定、清空一个队列里的消息等等
     */
    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * 将queue注入容器,spirng会在使用是自动声明,默认queue绑定defual exchange
     *
     * @return
     */
    @Bean
    public Queue testQueue() {
        return new Queue("testQueue");
    }

    /**
     * 使用json的 message convert 默认使用simpleMessageConvert
     *
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
