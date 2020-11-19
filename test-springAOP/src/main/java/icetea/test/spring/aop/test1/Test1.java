package icetea.test.spring.aop.test1;

import org.springframework.context.annotation.*;

/**
 * https://www.cnblogs.com/joy99/p/10941543.html
 */
@Configuration
@ComponentScan(basePackageClasses = IBuy.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Test1 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test1.class);
        Boy boy = context.getBean("boy", Boy.class);
        boy.buy();
        Girl girl = (Girl) context.getBean("girl2");
        girl.buy();
    }

    @Bean("girl2")
    public Girl girl() {
        return new Girl();
    }

}
