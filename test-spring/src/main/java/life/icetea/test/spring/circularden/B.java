package life.icetea.test.spring.circularden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {


    private C c;

    @Autowired
    public B(C c) {
        this.c = c;
    }

    public void method() {
        System.out.println("B.method");
    }
}
