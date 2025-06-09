package life.icetea.test.spring.autowired;

import life.icetea.test.spring.autowired.domain.IFather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    /**
     * 有多个IFather类型的对象，则会通过beanName注入
     */
    @Autowired
    private IFather father;

    public String getName() {
        return father.getName();
    }

}
