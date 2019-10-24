package icetea.spring.cloud.eureka.client.controller;

import icetea.spring.cloud.eureka.client.discoveryClient.TwoDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientOneController {

    @Autowired
    TwoDiscoveryClient twoDiscoveryClient;

    @GetMapping("test")
    public String test() {
        return twoDiscoveryClient.test1();
    }
}
