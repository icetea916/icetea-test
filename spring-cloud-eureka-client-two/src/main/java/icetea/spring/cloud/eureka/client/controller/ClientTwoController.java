package icetea.spring.cloud.eureka.client.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientTwoController {

    @GetMapping("test")
    public String test() {
        return "ok";
    }
}
