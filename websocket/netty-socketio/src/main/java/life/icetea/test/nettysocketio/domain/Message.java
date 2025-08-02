package life.icetea.test.nettysocketio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {

    private Date time;
    private String username;
    private T content;

}
