import domain.UserDTO;
import domain.UserEntity;
import org.junit.Test;

/**
 * BeanCopier test
 *
 * @author icetea
 */
public class BeanCopierUtilsTest {

    @Test
    public void test1() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(18);
        userEntity.setName("icetea");

        UserDTO userDTO1 = new UserDTO();
        BeanCopierUtils.copyProperties(userEntity, userDTO1);
        System.out.println("userDTO1=" + userDTO1);

        UserDTO userDTO2 = new UserDTO();
        BeanCopierUtils.copyProperties(userEntity, userDTO2);
        System.out.println("userDTO2" + userDTO2);
    }

}
