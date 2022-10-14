package life.icetea.test.spring.beanpostprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author icetea
 */
@Configuration
public class BeanConfiguration {

    @Bean(initMethod = "init", destroyMethod = "destroyUser")
    public User1 user1() {
        return new User1();
    }

    @Bean
    public MyBeanPostProcessor getMyBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

}
