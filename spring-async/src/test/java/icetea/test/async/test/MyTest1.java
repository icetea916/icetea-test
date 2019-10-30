package icetea.test.async.test;

import icetea.test.asyn.AsyncApplication;
import icetea.test.asyn.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = AsyncApplication.class)
@RunWith(SpringRunner.class)
public class MyTest1 {

    @Autowired
    private MyService myService;


    @Test
    public void test1() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(20);
        for (int i = 0; i < 10; i++) {
            myService.service1(cdl, i);
            myService.service2(cdl, i);
        }
        cdl.await();
        System.out.println("==========结束===========");
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            myService.service3(i);
            myService.service4(i);
        }
    }

}
