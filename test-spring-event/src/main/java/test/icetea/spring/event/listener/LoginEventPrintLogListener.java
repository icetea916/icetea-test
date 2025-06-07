package test.icetea.spring.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import test.icetea.spring.event.event.LoginEvent;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LoginEventPrintLogListener {


    @EventListener
    public void onLogin(LoginEvent loginEvent) {
        String username = loginEvent.getUsername();
        // 默认同步处理
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 发送消息通知用户
        log.info("message send User Logged in: {}", username);


    }

}
