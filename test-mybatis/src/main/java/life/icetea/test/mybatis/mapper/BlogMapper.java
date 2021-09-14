package life.icetea.test.mybatis.mapper;

import life.icetea.test.mybatis.domain.Blog;

public interface BlogMapper {

    Blog selectBlog(Integer id);

}
