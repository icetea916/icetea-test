package life.icetea.test.repository;

import life.icetea.test.domain.User;
import org.springframework.beans.factory.BeanFactory;

import java.util.Collection;

/**
 * @author icetea
 */
public class UserRepository {

    // 演示注入集合对象
    private Collection<User> users;

    // 演示注入spring内建对象,容器注入的并非所看到的BeanFactory而是DefaultListableBeanFactory
    private BeanFactory beanFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
