package icetea.spring.cloud.eureka.client.controller;

import icetea.spring.cloud.eureka.client.discoveryClient.LoadBalanceClient;
import icetea.spring.cloud.eureka.client.discoveryClient.TwoDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientOneController {

    @Autowired
    TwoDiscoveryClient twoDiscoveryClient;
    @Autowired
    private LoadBalanceClient loadBalanceClient;

    @GetMapping("test")
    public String test() {
        return twoDiscoveryClient.test1();
    }

    @GetMapping("test2")
    public String test2() {
        return loadBalanceClient.test1();
    }

}
