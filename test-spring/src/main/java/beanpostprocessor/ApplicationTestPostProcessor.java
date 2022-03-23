package beanpostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author icetea
 */
public class ApplicationTestPostProcessor {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);



        // 手动执行close方法
        applicationContext.close();
    }
}
