package icetea.rabbitmq.test;


import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.IOException;

/**
 * 自定义message转换器
 */
public class CustomMessageConvert implements MessageConverter {

    public static final String CHARSET_NAME = "UTF-8";

    /**
     * Object转JSON字符串
     *
     * @param object
     * @param messageProperties
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        // 约定，发送消息的除了javabean和map和String, 不会有其他类型
        byte[] bytes = null;
        try {
            String jsonString = null;
            if (object instanceof String) {
                jsonString = (String) object;
            } else {
                jsonString = JSONObject.toJSONString(object);
            }
            bytes = jsonString.getBytes(CHARSET_NAME);
        } catch (IOException e) {
            throw new MessageConversionException("springAMQP消息转换器 转换失败", e);
        }
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON); // 设置消息类型为json
        messageProperties.setContentEncoding(CHARSET_NAME); // 设置消息编码为utf-8
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT); // 设置消息持久化
        // 设置消息长度
        if (bytes != null) {
            messageProperties.setContentLength(bytes.length);
        }

        return new Message(bytes, messageProperties);
    }

    /**
     * message转成JSONObject类型
     *
     * @param message
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        Object content = null;
        MessageProperties properties = message.getMessageProperties();
        if (properties != null) {
            String contentType = properties.getContentType();
            // 是json消息
            if (contentType != null && contentType.contains("json")) {
                String encoding = properties.getContentEncoding();
                if (encoding == null) {
                    encoding = CHARSET_NAME;
                }
                try {
                    content = JSONObject.parseObject(new String(message.getBody(), encoding));
                } catch (IOException e) {
                    throw new MessageConversionException("Failed to convert Message content", e);
                }
            } else {
                // 不是json消息
                throw new MessageConversionException("不能转换content-type=[" + contentType + "] 的消息");
            }
        }

        if (content == null) {
            content = message.getBody();
        }

        return content;
    }
}
