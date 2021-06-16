package life.icetea.test.mybatis.demo1;

import life.icetea.test.mybatis.Blog;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper {

    Blog selectBlog(Integer id);

    @Select("select * from blog where id = #{id}")
    Blog selectBlog2(Integer id);

}
