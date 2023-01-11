package life.icetea.test.spring.componentscan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * #{@link ComponentScan} 注解可以将指定扫描路径下的类装配到Bean容器中
 * 默认扫描当前配置类包和子包下的类
 *
 * @author icetea
 */
@ComponentScan
public class TestComponentScan {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestComponentScan.class);
    }

}
