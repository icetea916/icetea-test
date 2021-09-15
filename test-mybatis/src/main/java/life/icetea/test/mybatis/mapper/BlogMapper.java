package life.icetea.test.mybatis.mapper;

import life.icetea.test.mybatis.domain.Blog;

import java.util.List;

public interface BlogMapper {

    Blog selectBlog(Integer id);

    int insertBlog(Blog blog);

    int insertBlogWithSelectKey(Blog blog);

    int insertBlogBatch(List<Blog> blogs);

    int updateBlog(Blog blog);

    int deleteById(Integer id);
}
