package life.icetea.test.spring.beanpostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试各种bean初始化方法的执行顺序
 *
 * @author icetea
 */
@Configuration
public class TestBeanPostProcessor {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBeanPostProcessor.class);

        // 手动执行close方法
        applicationContext.close();
    }

    /**
     * 注入user bean
     */
    @Bean(initMethod = "init", destroyMethod = "destroyUser")
    public User user() {
        return new User();
    }

    /**
     * 注入 beanPostProcessor
     *
     * @return
     */
    @Bean
    public MyBeanPostProcessor getMyBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

}
