package life.icetea.test.spring.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author icetea
 */
public class ApplicationTestXml {

    @Test
    public void testXml() {
        // 使用ClassPathXmlApplicationContext获取spring容器ApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        // 根据bean id获取bean对象
        User user = (User) applicationContext.getBean("user");
        System.out.println(user);
    }
}
