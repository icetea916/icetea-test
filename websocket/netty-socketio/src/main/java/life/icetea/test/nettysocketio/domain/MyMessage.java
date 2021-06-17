package life.icetea.test.nettysocketio.domain;

import lombok.Data;

@Data
public class MyMessage {

    private String username;
    private Integer age;
    private String content;
    private MyMessage msg;

    public MyMessage() {
    }

    public MyMessage(String username, Integer age, String content) {
        this.username = username;
        this.age = age;
        this.content = content;
    }

}
