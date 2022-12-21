package life.icetea.test.springredis.dao;

import com.alibaba.fastjson.JSON;
import life.icetea.test.springredis.cacheobject.UserCacheObject;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author icetea
 * @date 2022-12-21 21:51
 */
@Repository
public class UserCacheDAO {

    private static final String KEY_PATTERN = "user:%d"; // user:用户编号 <1>

    @Resource(name = "redisTemplate")
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ValueOperations<String, String> operations; // <2>

    private static String buildKey(Integer id) { // <3>
        return String.format(KEY_PATTERN, id);
    }

    public UserCacheObject get(Integer id) {
        String key = buildKey(id);
        String value = operations.get(key);
        return JSON.parseObject(value, UserCacheObject.class);
    }

    public void set(UserCacheObject object) {
        String key = buildKey(object.getId());
        String value = JSON.toJSONString(object);
        operations.set(key, value);
    }


}
