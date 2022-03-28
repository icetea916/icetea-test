package life.icetea.test;

import life.icetea.test.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入 demo
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository.getUsers().toString());

        // 此处获取到的是依赖注入的对象
        BeanFactory beanFactory1 = userRepository.getBeanFactory();
        System.out.println(beanFactory1);
        System.out.println(beanFactory1 == beanFactory);

        // 如果BeanFactory是一个bean的话会根据类型查询到,但是一下代码会报错，所以BeanFactory不是普通的bean
        System.out.println(beanFactory.getBean(BeanFactory.class));

    }


}
