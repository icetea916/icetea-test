package icetea.spring.cloud.eureka.client.discoveryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 使用discoverClient获取服务并用restTemplate调用示例
 */
@Component
public class TwoDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String test1() {
        List<ServiceInstance> instances = discoveryClient.getInstances("client-two");

        if (instances == null || instances.size() == 0) {
            return null;
        }

        String serviceUri = String.format("%s/test-feign", instances.get(0).getUri());

        // 用用RestTemplate进行调用
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null
                , String.class);

        return exchange.getBody();
    }
}
