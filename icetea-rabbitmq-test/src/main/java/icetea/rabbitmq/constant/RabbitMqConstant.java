package icetea.rabbitmq.constant;

/**
 * 队列常量
 *
 * @author icetea
 */
public class RabbitMqConstant {
    // 交换机常量
    public static final String EXCHANGE_ICETEA_DIRECT = "icetea.direct";
    public static final String EXCHANGE_ICETEA_TOPIC = "icetea.topic";

    // 队列常量
    public static final String QUEUE_ICETEA_TEST_1 = "icetea.test1";
    public static final String QUEUE_DLX_ICETEA_TEST1 = "icetea.test1.dlx";
    public static final String QUEUE_ICETEA_TEST_2 = "icetea.test2";

    private void RabbitMqConstant() {
    }
}
