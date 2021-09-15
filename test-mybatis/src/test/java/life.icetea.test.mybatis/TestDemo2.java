package life.icetea.test.mybatis;

import life.icetea.test.mybatis.domain.Blog;
import life.icetea.test.mybatis.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * 使用
 */
public class TestDemo2 {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * 加载xml配置构造sqlsessionFactory
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * 使用sqlSession执行操作
     */
    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 方式一
        Blog blog = sqlSession.selectOne("life.icetea.test.mybatis.mapper.BlogMapper.selectBlog", 1);
        System.out.println("方式一: blog=" + blog);

        // 方式二
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog2 = mapper.selectBlog(2);
        System.out.println("方式二: blog=" + blog2);

        sqlSession.close();
    }

}
