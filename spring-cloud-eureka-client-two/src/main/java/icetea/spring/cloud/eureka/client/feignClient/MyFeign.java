package icetea.spring.cloud.eureka.client.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("client-one")
public interface MyFeign {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    String test();

}
