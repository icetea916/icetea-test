package icetea.test.webSocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务器向浏览器发送的此类消息。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String responseMessage;
}
