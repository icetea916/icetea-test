package life.icetea.test.springboot.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author icetea
 * @date 2023-02-06 15:36
 */
@SpringBootApplication
@EnableAsync
public class TestAsyncApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestAsyncApplication.class, args);

        DemoService demoService = applicationContext.getBean(DemoService.class);
        demoService.async("icetea");

        // 执行异步线程任务，出现异常捕获不到
        try {
            demoService.asyncThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("执行main线程");
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
