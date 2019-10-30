package icetea.test.asyn.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class MyService {

    @Async
    public void service1(CountDownLatch cdl, int i) {
        System.out.println("service11111=" + i);
        cdl.countDown();
    }

    @Async
    public void service2(CountDownLatch cdl, int i) {
        System.out.println("service22222=" + i);
        cdl.countDown();
    }

    public void service3(int i) {
        System.out.println("service3333=" + i);
    }

    public void service4(int i) {
        System.out.println("service4444=" + i);
    }
}
