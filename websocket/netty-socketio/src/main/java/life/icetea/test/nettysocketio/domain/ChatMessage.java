package life.icetea.test.nettysocketio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推送消息
 *
 * @author icetea
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage<T> {

    private String username;
    private T message;

}
