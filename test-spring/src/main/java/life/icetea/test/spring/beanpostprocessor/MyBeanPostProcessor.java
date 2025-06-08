package life.icetea.test.spring.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 定义一个BeanPostProcessor
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * @param bean
     * @param beanName
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        // 这边只做简单打印   原样返回bean
        System.out.println("postProcessBeforeInitialization====" + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        // 这边只做简单打印   原样返回bean
        System.out.println("postProcessAfterInitialization====" + beanName);
        return bean;
    }

}