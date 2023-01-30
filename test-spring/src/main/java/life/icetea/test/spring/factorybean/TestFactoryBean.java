package life.icetea.test.spring.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * FactoryBean是spring bean中的一种类型(spring中的bean有两种类型：一种是普通Bean，一种是工厂Bean即FactoryBean)
 * FactoryBean是一个工厂Bean，里面的getObject方法就是创建Bean的对象方法。
 * 一般用于创建比较复杂的bean。
 * 当实例化Bean过程比较复杂时，需要在bean中提供大量配置信息，这时可以采用FactoryBean来通过java逻辑去实践复杂的bean实例化操作
 *
 * @author icetea
 * @date 2023-01-11 13:46
 */
@ComponentScan
public class TestFactoryBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestFactoryBean.class);

        // 容器中所有bean名称
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);

        // 获取user类型也是通过userFactory获取的
        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        // 获取beanFactory类可以直接获取工厂类创建的Bean
        Object user3 = applicationContext.getBean("userFactory");
        // 要想获取FactoryBean本身，则要在beanName前面加上&
        Object userFactory = applicationContext.getBean("&userFactory");

        System.out.println("================>>");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(userFactory);
        System.out.println(user1 == user2);
    }

}
