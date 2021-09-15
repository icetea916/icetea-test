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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用xml构建SqlSessionFactory
 * 并使用sqlSession进行简单查使用
 */
public class TestDemo1 {

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

    /**
     * 测试插入一个
     */
    @Test
    public void testInsertOne() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        // 使用useGeneratedKeys获取插入后的主键
        Blog blog = new Blog();
        blog.setText("icetea-test-insert-one");
        int i = mapper.insertBlog(blog);
        System.out.println("blog=" + blog + ", i=" + i);

        // 使用selectKey子元素获取插入后的主键
        Blog blog2 = new Blog();
        blog2.setText("icetea-test-insert-one-by-selectKey");
        int i2 = mapper.insertBlogWithSelectKey(blog2);
        System.out.println("blog2=" + blog2 + ", i2=" + i2);

        // 注意必须提交才能插入成功
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试批量插入
     */
    @Test
    public void testInsertBatch() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Blog> blogs = new LinkedList<>();
        Blog blog1 = new Blog();
        blog1.setText("batch-insert-text1");
        blogs.add(blog1);
        Blog blog2 = new Blog();
        blog2.setText("batch-insert-text2");
        blogs.add(blog2);
        Blog blog3 = new Blog();
        blog3.setText("batch-insert-text3");
        blogs.add(blog3);

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.insertBlogBatch(blogs);
        System.out.println("blogs=" + blogs);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        Blog blog = new Blog();
        blog.setId(1);
        blog.setText("batch-update");

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.updateBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.deleteById(1);

        sqlSession.commit();
        sqlSession.close();
    }

}
