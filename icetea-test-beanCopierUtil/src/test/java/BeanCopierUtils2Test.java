import domain.UserDTO;
import domain.UserEntity;
import org.junit.Test;

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

        UserDTO userDTO1 = new UserDTO();
        BeanCopierUtils2.copyProperties(userEntity, userDTO1);
        System.out.println("userDTO1=" + userDTO1);

        UserDTO userDTO2 = new UserDTO();
        BeanCopierUtils2.copyProperties(userEntity, userDTO2);
        System.out.println("userDTO2" + userDTO2);
    }

}
