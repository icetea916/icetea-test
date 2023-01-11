package life.icetea.test.spring.beanpostprocessor;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 定义一个实现InitializingBean DisposableBean的bean
 */
public class User implements InitializingBean, DisposableBean {

    static {
        System.out.println("User静态代码块被调用......");
    }

    public User() {
        System.out.println("User的构造方法被调用......");
    }

    /**
     * InitializingBean 的 after方法
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean的afterPropertiesSet方法被调用......");
    }

    /**
     * user内部init方法
     */
    private void init() {
        System.out.println("User内部initMethod方法被调用.....");
    }


    /**
     * DisposableBean 的 destroy方法
     *
     * @throws Exception
     */
    public void destroy() throws Exception {
        System.out.println("DisposableBean的destroy方法被调用....");
    }

    /**
     * 接口内部destroy方法
     */
    private void destroyUser() {
        System.out.println("User内部destroyMethod方法被调用......");
    }
}