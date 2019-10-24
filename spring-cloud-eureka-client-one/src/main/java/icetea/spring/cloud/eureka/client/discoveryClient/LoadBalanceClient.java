package icetea.spring.cloud.eureka.client.discoveryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoadBalanceClient {

    @Autowired
    private RestTemplate restTemplate;

    public String test1() {
        String serviceUri = "http://client-two/test";
        ResponseEntity<String> exchange = restTemplate.exchange(
                serviceUri,
                HttpMethod.GET,
                null,
                String.class);

        return exchange.getBody();
    }
}
