package life.icetea.test;

import life.icetea.test.annotation.Super;
import life.icetea.test.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 不同方式的 依赖查找
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        // 实时查找
//        lookupInTime(beanFactory);
        // 延时查找
//        lookupByLazy(beanFactory);
        equalLazyAndInTime(beanFactory);
        // 根据类型查找
//        lookupByType(beanFactory);
        // 查找类型集合
//        lookupCollectionByType(beanFactory);
        // 根据id和class查找
//        lookupByIdAndType(beanFactory);
        // 根据注解查找
//        lookupByAnnotation(beanFactory);
    }

    /**
     * 根据注解查找
     *
     * @param beanFactory
     */
    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("根据注解annotation查找bean集合:" + beans);
        }
    }

    /**
     * 根据id和type查询bean
     *
     * @param beanFactory
     */
    private static void lookupByIdAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("根据id和type查找:" + user);
    }


    /**
     * 根据类型查找bean的集合
     *
     * @param beanFactory
     */
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("根据类型查找bean集合:" + map);
        }
    }

    /**
     * 按照类型查找
     *
     * @param beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User bean = beanFactory.getBean(User.class);
        System.out.println("根据类型查找:" + bean);
    }

    /**
     * 实时查找,根据bean id
     */
    public static void lookupInTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找: " + user);
    }

    /**
     * 延迟查找依赖
     */
    public static void lookupByLazy(BeanFactory beanFactory) {
        // objectFactory不生成新的bean, 用factoryBean查找时会生成新的bean
        ObjectFactory<User> objectFactory = (ObjectFactory) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找: " + user);
    }

    public static void equalLazyAndInTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找: " + user);
        // objectFactory不生成新的bean, 用factoryBean查找时会生成新的bean
        ObjectFactory<User> objectFactory = (ObjectFactory) beanFactory.getBean("objectFactory");
        User user2 = objectFactory.getObject();
        System.out.println("延迟查找: " + user2);
        System.out.println("equal=" + (user == user2));
    }

}
