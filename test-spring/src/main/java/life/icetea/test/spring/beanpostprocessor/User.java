package life.icetea.test.spring.beanpostprocessor;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * 定义一个实现InitializingBean DisposableBean的bean
 */
public class User implements InitializingBean, DisposableBean {

    @Value("icetea static")
    private static String staticname;

    @Autowired
    private static B staticB;

    private String name;

    private B b;

    static {
        System.out.println("User静态代码块被调用......");
    }

    {
        System.out.println("User初始化代码块被调用.....");
    }

//    public User() {
//        System.out.println("User无参构造方法被调用......");
//    }

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

    @PostConstruct
    public void postConstruct() {
        System.out.println("User调用postConstruct方法......");
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

    public User(String name) {
        this.name = name;
        System.out.println("User有参构造方法被调用......");
    }

    public String getName() {
        return name;
    }

    @Value("icetea setName")
    public void setName(String name) {
        System.out.println("user的setName方法被调用......");
        this.name = name;
    }

    public B getB() {
        return b;
    }

    @Autowired
    public void setB(B b) {
        System.out.println("user的setB方法被调用......");
        this.b = b;
    }
}