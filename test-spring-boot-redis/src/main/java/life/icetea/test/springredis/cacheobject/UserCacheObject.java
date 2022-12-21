package life.icetea.test.springredis.cacheobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCacheObject {

    private Integer id;
    private String username;
    private Integer age;

}
