package life.icetea.test.mybatis.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("sysUser")
public class SysUser {

    private Integer id;
    private String username;
    private String password;
    private String tel;
    private Integer roleId;

}
