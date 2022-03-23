package annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义一个注解配置文件 必须要加上@Configuration注解
 */
@Configuration
public class MainConfig {

    /**
     * 定义一个bean对象
     *
     * @Bean 具体参数详解
     * value -- bean别名和name是相互依赖关联的，value,name如果都使用的话值必须要一致
     * name -- bean名称，如果不写会默认为注解的方法名称
     * autowire -- 自定装配默认是不开启的，建议尽量不要开启，因为自动装配不能装配基本数据类型、字符串、数组等，这是自动装配设计的局限性，以及自动装配不如显示依赖注入精确
     * initMethod -- bean的初始化之前的执行方法，该参数一般不怎么用，因为可以完全可以在代码中实现
     * destroyMethod -- bean销毁执行的方法
     */
    @Bean(initMethod = "initUser", destroyMethod = "destroyUser")
    public User getUser() {
        return new User("icetea-configuration", 27);
    }

}