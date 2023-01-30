package life.icetea.test.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * user factory bean 示例
 *
 * @author icetea
 * @date 2023-01-11 15:02
 */
@Component("userFactory")
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        System.out.println("====>getObject");
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    /**
     * 是否单例，true则user实例只会创建一次，即getObject只会被调用一次，false则每次都会调用getObject来创建user实例
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

}
