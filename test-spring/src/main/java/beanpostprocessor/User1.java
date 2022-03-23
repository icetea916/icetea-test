package beanpostprocessor;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 定义一个实现InitializingBean DisposableBean的bean
 */
public class User1 implements InitializingBean, DisposableBean {

    public User1() {
        System.out.println("实例化User1的构造方法......");
    }

    public void destroy() throws Exception {
        System.out.println("调用实现DisposableBean的destroy方法....");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("调用实现InitializingBean的afterPropertiesSet方法......");
    }

    public void initUser() {
        System.out.println("执行initMethod方法.....");
    }

    public void destroyUser() {
        System.out.println("执行destroyMethod方法......");
    }
}