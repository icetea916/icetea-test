package life.icetea.test.thinkinspring.ioc.overview.dependency.lookup;

import life.icetea.test.thinkinspring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖查找示例
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        lookupInTime(beanFactory);
        lookupByLazy(beanFactory);
    }

    /**
     * 实时查找依赖
     */
    public static void lookupInTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找: " + user);
    }

    /**
     * 延迟查找依赖
     */
    public static void lookupByLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory)beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找: " + user);
    }

}
