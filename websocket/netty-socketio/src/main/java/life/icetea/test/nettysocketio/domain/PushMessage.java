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
public class PushMessage {

    private String username;
    private Integer age;
    private String content;

}
