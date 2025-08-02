package life.icetea.test.spring.circularden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {


    private B b;

    @Autowired
    public A(B b) {
        this.b = b;
    }


    public A() {
        // 构造函数中就调用 B，此时 classB 还未被注入
//        b.method(); // 💥 NullPointerException 或循环依赖失败
    }


}
