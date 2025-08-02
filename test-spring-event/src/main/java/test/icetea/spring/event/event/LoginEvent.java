package test.icetea.spring.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 登录事件
 */
public class LoginEvent extends ApplicationEvent {

    private final String username;

    public LoginEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
