package annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author icetea
 */
public class ApplicationTestAnnotation {

    public static void main(String[] args) {
        // 使用AnnotationConfigApplicationContext获取spring容器ApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        User bean2 = applicationContext.getBean(User.class);
        System.out.println(bean2);

        String[] namesForType = applicationContext.getBeanNamesForType(User.class);
        for (String name : namesForType) {
            System.out.println("bean名称为===" + name);
        }
        // 手动执行close方法
        applicationContext.close();
    }
}
