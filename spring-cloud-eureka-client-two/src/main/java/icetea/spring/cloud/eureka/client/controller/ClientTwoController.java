package icetea.spring.cloud.eureka.client.controller;


import icetea.spring.cloud.eureka.client.feignClient.MyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientTwoController {

    @Autowired
    MyFeign myFeign;

    @GetMapping("test")
    public String test() {
        return "ok";
    }

    @GetMapping("test-feign")
    public String testFeignClient() {
        return myFeign.test();
    }
}
