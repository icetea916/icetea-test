package life.icetea.test.springboot.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author icetea
 * @date 2023-02-06 15:39
 */
@Service
public class DemoService {

    public static final Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Async
    public void async(String name) {
        logger.info("执行异步任务,name={}", name);
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void asyncThrowException() {
        logger.info("执行异常异步任务");
        int i = 1 / 0;
    }

}
