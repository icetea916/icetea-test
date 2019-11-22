package icetea.spring.cloud.eureka.client.hystrix;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import icetea.spring.cloud.eureka.client.hystrix.service.TestHystrixService;
import icetea.util.user.UserContext;
import icetea.util.user.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("hystrix")
public class TestHystrix {
    private static final Logger logger = LoggerFactory.getLogger(TestHystrix.class);

    @Autowired
    private TestHystrixService testHystrixService;

    @RequestMapping("test1")
    public String testHystrix() {
        List<String> list = testHystrixService.getList();

        return list.toString();
    }

    @RequestMapping("testRandomSleep")
    public String testRandomHystrix() {
        List<String> list = new ArrayList<>();
        try {
            list = testHystrixService.randomGetList();
        } catch (HystrixRuntimeException e) {
            System.out.println("hystrix调用超时");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("线程中断异常");
        }

        return list.toString();
    }

    @RequestMapping("testThreadLocal")
    public String testThreadLocal() {
        UserContext context = UserContextHolder.getContext();
        logger.info(UserContext.CORRELATION_ID + ":" + context.getCorrelationId());

        testHystrixService.testThreadLocal();

        return "test";
    }

}
