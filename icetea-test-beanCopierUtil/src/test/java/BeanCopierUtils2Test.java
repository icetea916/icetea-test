import life.icetea.test.beancopire.BeanCopierUtils2;
import life.icetea.test.beancopire.domain.AccountEntity;
import life.icetea.test.beancopire.domain.UserDTO;
import life.icetea.test.beancopire.domain.UserEntity;
import life.icetea.test.beancopire.domain.converter.AccountEntityConvert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * BeanCopier2 test
 *
 * @author icetea
 */
public class BeanCopierUtils2Test {

    @Test
    public void test1() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(18);
        userEntity.setName("icetea");
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(100);
        accountEntity.setBalance(new BigDecimal("889.99"));
        accountEntity.setCreateTime(new Date());
        userEntity.setAccount(accountEntity);
        System.out.println("userEntity=" + userEntity);

        UserDTO userDTO1 = new UserDTO();
        BeanCopierUtils2.copyProperties(userEntity, userDTO1);
        System.out.println("userDTO1=" + userDTO1);

        UserDTO userDTO2 = new UserDTO();
        BeanCopierUtils2.copyProperties(userEntity, userDTO2);
        System.out.println("userDTO2" + userDTO2);
    }

    /**
     * 测试带converter的
     */
    @Test
    public void test2() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(28);
        userEntity.setName("icetea2");
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(180);
        accountEntity.setBalance(new BigDecimal("888.88"));
        accountEntity.setCreateTime(new Date());
        userEntity.setAccount(accountEntity);
        System.out.println("userEntity=" + userEntity);

        UserDTO userDTO1 = new UserDTO();
        BeanCopierUtils2.copyProperties(userEntity, userDTO1, new AccountEntityConvert());
        System.out.println("userDTO1=" + userDTO1);

        UserDTO userDTO2 = new UserDTO();
        BeanCopierUtils2.copyProperties(userEntity, userDTO2, new AccountEntityConvert());
        System.out.println("userDTO2" + userDTO2);
    }

}
