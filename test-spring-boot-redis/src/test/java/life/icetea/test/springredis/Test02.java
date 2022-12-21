package life.icetea.test.springredis;

import life.icetea.test.springredis.cacheobject.UserCacheObject;
import life.icetea.test.springredis.dao.UserCacheDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author icetea
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test02 {

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private UserCacheDAO userCacheDAO;

    @Before
    public void init() {
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
    }

    @Test
    public void testDAO01() {
        UserCacheObject obj = new UserCacheObject();
        obj.setId(2);
        obj.setUsername("icetea");
        obj.setAge(18);

        userCacheDAO.set(obj);
        log.info("obj={}", obj);
    }

    @Test
    public void testDAO() {
        UserCacheObject userCacheObject = userCacheDAO.get(1);
        log.info("obj={}", userCacheObject);
    }

}
