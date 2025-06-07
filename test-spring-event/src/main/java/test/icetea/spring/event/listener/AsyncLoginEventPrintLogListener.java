package test.icetea.spring.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import test.icetea.spring.event.event.LoginEvent;

@Slf4j
@Component
public class AsyncLoginEventPrintLogListener {


    /**
     * 异步处理
     *
     * @param loginEvent
     */
    @EventListener
    @Async
    public void onLogin(LoginEvent loginEvent) {
        String username = loginEvent.getUsername();
        // 发送消息通知用户
        log.info("async message send User Logged in: {}", username);
    }

}
