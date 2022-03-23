package beanpostprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author icetea
 */
@Configuration
public class BeanConfiguration {

    @Bean(initMethod = "initUser", destroyMethod = "destroyUser")
    public User1 getUser1() {
        return new User1();
    }

    @Bean
    public MyBeanPostProcessor getMyBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

}
