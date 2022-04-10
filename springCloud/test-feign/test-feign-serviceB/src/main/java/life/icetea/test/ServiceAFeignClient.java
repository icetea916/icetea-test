package life.icetea.test;

import life.icetea.test.config.MyFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(value = "ServiceA", configuration = MyFeignConfiguration.class)
//@RibbonClient(value = "ServiceA", configuration = MyRibbonConfiguration.class) 指定对服务A的调用负载均衡算法
public interface ServiceAFeignClient extends ServiceAInterface {

}