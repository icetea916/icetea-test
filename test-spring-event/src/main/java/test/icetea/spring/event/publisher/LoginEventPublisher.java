package test.icetea.spring.event.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import test.icetea.spring.event.event.LoginEvent;

@Service
public class LoginEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public LoginEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishLoginEvent(String username) {
        LoginEvent loginEvent = new LoginEvent(this, username);
        eventPublisher.publishEvent(loginEvent);
    }

}
