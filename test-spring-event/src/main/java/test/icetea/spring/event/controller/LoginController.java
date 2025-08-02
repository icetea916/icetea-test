package test.icetea.spring.event.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.icetea.spring.event.publisher.LoginEventPublisher;

@RestController
public class LoginController {

    @Autowired
    private LoginEventPublisher loginEventPublisher;


    @RequestMapping("login")
    public String login(String username) {
        loginEventPublisher.publishLoginEvent(username);

        return "success";
    }

}
