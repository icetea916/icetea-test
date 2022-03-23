package xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author icetea
 */
public class ApplicationTestXml {

    public static void main(String[] args) {
        // 使用ClassPathXmlApplicationContext获取spring容器ApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // 根据bean id获取bean对象
        User bean = (User) applicationContext.getBean("user");
        System.out.println(bean);
    }
}
