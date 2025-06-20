package life.icetea.test.spring.circularden;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class TestCircularDependency {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestCircularDependency.class);


        A a = context.getBean(A.class);
        System.out.println(a);
    }


}
