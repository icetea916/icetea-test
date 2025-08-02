package life.icetea.test.spring.circularden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class C {


    private A a;

    @Autowired
    public C(A a) {
        this.a = a;
    }

}
