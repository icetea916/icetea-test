package icetea.test.asyn.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Async
    public void service1(int i) {
        System.out.println("service11111=" + i);
    }

    @Async
    public void service2(int i) {
        System.out.println("service22222=" + i);
    }

    public void service3(int i) {
        System.out.println("service3333=" + i);
    }

    public void service4(int i) {
        System.out.println("service4444=" + i);
    }
}
