package icetea.util.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class AmqpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpUtils.class);
    /**
     * 最大重试次数
     */
    private static final int MAX_RETRY_TIMES = 3;
    /**
     * 重试次数在消息头中的head的key值
     */
    private static final String RETRY_HEAD_KEY = "x-retry-time";
    /**
     * 默认死信交换器
     */
    private static final String DEFAULT_DLX = "icetea.direct";
    /**
     * 异常栈保留最字符数
     */
    private static final int MAX_STACK_TRACE_LENGTH = Integer.MAX_VALUE;

    private AmqpTemplate amqpTemplate;


    /**
     * 处理消息方法（带自动入队重试机制）
     *
     * @param message          消息
     * @param dlxRoutingKey    死信队列路由键（一般为死信队列名称）
     * @param messageProcessor 消息处理器
     */
    public void handleMessageWithRetry(Message message, String dlxRoutingKey, MessageProcessor messageProcessor) {
        MessageProperties messageProperties = message.getMessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();// 获取自定义头信息
        try {
            messageProcessor.processMessage(message);
        } catch (Exception e) {
            // 处理出现异常,进行重试
            int retryTimes = headers.containsKey(RETRY_HEAD_KEY) ? (int) headers.get(RETRY_HEAD_KEY) : 0;
            int nextRetryTimes = retryTimes + 1;
            if (nextRetryTimes > MAX_RETRY_TIMES) {
                // 超出最大尝试次数，将其放置到死信队列,并保存错误信息
                this.amqpTemplate.convertAndSend(DEFAULT_DLX, dlxRoutingKey, message,
                        (Message msg) -> {
                            msg.getMessageProperties().setHeader("x-exception-message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                            msg.getMessageProperties().setHeader("x-exception-stacktrace", getStackTraceString(e));
                            return msg;
                        });
                LOGGER.error("message={} 已超过最大重试次数，放置到死信队列中", new String(message.getBody()));
            } else {
                // 入队重试
                this.amqpTemplate.convertAndSend(messageProperties.getReceivedExchange(), messageProperties.getConsumerQueue(),
                        message, (Message msg) -> {
                            // 更新重试次数
                            msg.getMessageProperties().setHeader(RETRY_HEAD_KEY, nextRetryTimes);
                            return msg;
                        });
                LOGGER.info("message={}处理失败，进行第 {} 次重试", new String(message.getBody()), nextRetryTimes);
            }

        }
    }

    /**
     * 消息处理接口
     */
    public interface MessageProcessor {
        void processMessage(Message message);
    }

    /**
     * copy RepublishMessageRecoverer中的代码
     *
     * @param cause 异常
     * @return 根据最大保留字符数返回异常栈信息
     */
    private String getStackTraceString(Throwable cause) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        cause.printStackTrace(printWriter);
        String stackTraceAsString = stringWriter.getBuffer().toString();

        if (stackTraceAsString.length() > MAX_STACK_TRACE_LENGTH) {
            stackTraceAsString = stackTraceAsString.substring(0, MAX_STACK_TRACE_LENGTH);
        }

        return stackTraceAsString;
    }

    public AmqpUtils(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }
}
