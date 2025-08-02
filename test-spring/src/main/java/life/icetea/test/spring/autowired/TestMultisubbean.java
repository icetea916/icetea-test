package life.icetea.test.spring.autowired;

import life.icetea.test.spring.autowired.domain.IFather;
import life.icetea.test.spring.autowired.domain.SonA;
import life.icetea.test.spring.autowired.domain.SonB;
import life.icetea.test.spring.autowired.domain.SonC;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class TestMultisubbean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestMultisubbean.class);

        IFather bean = (IFather) context.getBean("father");
        System.out.printf("name=%s %n", bean.getName());
    }


    @Bean
    public SonA sonA() {
        return new SonA();
    }

    @Bean
    public SonB sonB() {
        return new SonB();
    }

    @Bean
    public SonC sonC() {
        return new SonC();
    }

    @Bean
    public IFather father(SonA sonA) {
        return sonA;
    }

}
