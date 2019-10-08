package icetea.rabbitmq.listener;

import icetea.rabbitmq.constant.RabbitMqConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = {RabbitMqConstant.QUEUE_ICETEA_TEST_1})
public class MyListener {

}
