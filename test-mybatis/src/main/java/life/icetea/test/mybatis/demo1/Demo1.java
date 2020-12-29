package life.icetea.test.mybatis.demo1;

import life.icetea.test.mybatis.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 使用xml构建SqlSessionFactory
 */
public class Demo1 {

    public static void main(String[] args) throws IOException {
//        test1();
        test2();
    }

    public static void test1() throws IOException {
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = factory.openSession();
        Blog blog = sqlSession.selectOne("life.icetea.test.mybatis.demo1.BlogMapper.selectBlog", 1);
        System.out.println(blog);
        sqlSession.close();
    }

    public static void test2() throws IOException {
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = factory.openSession();

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlog(1);
        System.out.println(blog);
        sqlSession.close();
    }

}
