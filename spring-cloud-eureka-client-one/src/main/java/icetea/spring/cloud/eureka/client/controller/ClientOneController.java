package icetea.spring.cloud.eureka.client.controller;

import icetea.spring.cloud.eureka.client.discoveryClient.LoadBalanceClient;
import icetea.spring.cloud.eureka.client.discoveryClient.TwoDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("test3")
    public String test3() {
        return "ok";
    }

    @GetMapping("router-filter")
    public Map<String, Object> routingFilterTest() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("serviceName", "client-one");
        map.put("active", "Y");
        map.put("endpoint", "router-filter");
        map.put("weight", 8);

        return map;
    }

}
