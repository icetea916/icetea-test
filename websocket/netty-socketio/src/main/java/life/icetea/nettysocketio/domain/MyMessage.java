package life.icetea.nettysocketio.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyMessage implements Serializable {

    private String username;
    private Integer age;
    private String content;

    public MyMessage() {
    }

    public MyMessage(String username, Integer age, String content) {
        this.username = username;
        this.age = age;
        this.content = content;
    }
}
