package life.icetea.test.springboot.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;

/**
 * @author icetea
 * @date 2024/5/17
 */
public class ExampleBean implements InitializingBean, BeanPostProcessor {

    static final Logger logger = LoggerFactory.getLogger(ExampleBean.class);

    @PostConstruct
    public void postConstruct() {
        logger.info("@PostConstruct执行成功");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("InitializeingBean afterPropertiesSet执行成功");
    }

    public void initMethod() {
        logger.info("initMethod执行成功");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.error("BeanPostProcessor postProcessBeforeInitialization执行成功");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.error("BeanPostProcessor postProcessAfterInitialization执行成功");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
