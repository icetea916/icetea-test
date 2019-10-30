package icetea.test.async.test;

import icetea.test.asyn.AsyncApplication;
import icetea.test.asyn.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AsyncApplication.class)
@RunWith(SpringRunner.class)
public class MyTest1 {

    @Autowired
    private MyService myService;


    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            myService.service1(i);
            myService.service2(i);
        }
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            myService.service3(i);
            myService.service4(i);
        }
    }

}
