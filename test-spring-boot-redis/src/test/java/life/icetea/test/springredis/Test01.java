package life.icetea.test.springredis;

import life.icetea.test.springredis.cacheobject.UserCacheObject;
import life.icetea.test.springredis.dao.UserCacheDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author icetea
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test01 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private UserCacheDAO userCacheDAO;

    /**
     * 简单操作stringRedisTemplate
     * 该模板的都使用StringRedisSerializer进行序列化操作
     */
    @Test
    public void testStringRedisTemplateSetKey() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("icetea-01", "icetea-value");
    }

    /**
     * 简单操作redisTemplate
     * 该模板的默认使用JdkSerializationRedisSerializer进行序列化操作，会出现乱码，所以一般不使用
     */
    @Test
    public void testRedisTemplateSetKey() {
        redisTemplate.opsForValue().set("icetea-02", "icetea-value");
    }

    /**
     * 使用GenericJackson2JsonRedisSerializer序列化数据
     */
    @Test
    public void testGenericJackson2JsonRedisSerializer() {
        UserCacheObject userCacheObject = new UserCacheObject();
        userCacheObject.setId(1);
        userCacheObject.setUsername("icetea");
        userCacheObject.setAge(18);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        ValueOperations<String, UserCacheObject> valueOperations = redisTemplate.opsForValue();
        String key = String.format("user:%d", userCacheObject.getId());
        valueOperations.set(key, userCacheObject);
    }

    @Test
    public void testGenericJackson2JsonRedisSerializerGet() {
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());

        String key = String.format("user:%d", 1);
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
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
