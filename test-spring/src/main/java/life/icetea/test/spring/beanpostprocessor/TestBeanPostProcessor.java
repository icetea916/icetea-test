package life.icetea.test.spring.beanpostprocessor;

import org.springframework.context.annotation.*;

/**
 * 测试各种bean初始化方法的执行顺序
 *
 * @author icetea
 */
@Configuration
@ComponentScan
public class TestBeanPostProcessor {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBeanPostProcessor.class);

        User user = applicationContext.getBean(User.class);
        System.out.println("username=" + user.getName());
        System.out.println("userB=" + user.getB());
        // 手动执行close方法
        applicationContext.close();
    }

    /**
     * 注入user bean
     */
    @Bean(initMethod = "init", destroyMethod = "destroyUser")
    public User user() {
        return new User("icetea");
    }

}
