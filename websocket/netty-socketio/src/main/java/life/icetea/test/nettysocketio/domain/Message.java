package life.icetea.test.nettysocketio.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 推送消息
 *
 * @author icetea
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sendDate;
    private String username;
    private T content;

}
