package life.icetea.test.mybatis.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("blog")
public class Blog {

    private Integer id;
    private String text;

}
