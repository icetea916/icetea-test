package icetea.spring.cloud.eureka.client.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import icetea.spring.cloud.eureka.client.hystrix.threadlocal.UserContext;
import icetea.spring.cloud.eureka.client.hystrix.threadlocal.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TestHystrixService {
    private static final Logger logger = LoggerFactory.getLogger(TestHystrixService.class);

    @HystrixCommand
    public List<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("李晓烨");
        list.add("我爱你");

        return list;
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            },
            fallbackMethod = "fallBackMethod",
            threadPoolKey = "test1",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }
    )
    public List<String> randomGetList() throws InterruptedException {
        Random random = new Random();

        int randomNum = random.nextInt(3) + 1;

        if (randomNum == 3) {
            Thread.sleep(11000);
        }

        ArrayList<String> list = new ArrayList<>();
        list.add("我爱你");
        list.add("李晓烨");

        return list;
    }


    public List<String> fallBackMethod() {
        ArrayList<String> list = new ArrayList<>();
        list.add("我爱你");
        list.add("李晓烨");
        list.add("forever");

        return list;
    }

    @HystrixCommand
    public void testThreadLocal() {
        // 使用hystrixCommand注解是取不到主线程的threadlocal值的
        UserContext context = UserContextHolder.getContext();
        logger.info(UserContext.CORRELATIKON_ID + ":" + context.getCorrelationId());
    }
}