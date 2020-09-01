package icetea.test.webSocket.domain;

import lombok.Data;

/**
 * 浏览器向服务器发送的消息使用此类接受
 */
@Data
public class Message {
    private String name;
    private String content;

}
