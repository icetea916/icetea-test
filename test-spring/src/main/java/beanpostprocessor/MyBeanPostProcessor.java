package beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 定义一个BeanPostProcessor
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * @param bean
     * @param beanName
     * @return
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