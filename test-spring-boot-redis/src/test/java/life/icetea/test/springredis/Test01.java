package life.icetea.test.springredis;

import life.icetea.test.springredis.pojo.User;
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
        User user = new User();
        user.setId(1);
        user.setUsername("icetea");
        user.setAge(18);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        String key = String.format("user:%d", user.getId());
        valueOperations.set(key, user);
    }

    @Test
    public void testGenericJackson2JsonRedisSerializerGet() {
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());

        String key = String.format("user:%d", 1);
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }

//    /**
//     * 使用GenericJackson2JsonRedisSerializer序列化数据 数组
//     */
//    @Test
//    public Object testGenericJackson2JsonRedisSerializerWithArray() {
//        String key = "icetea:testgj:array";
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        ValueOperations<String, List<User>> valueOperations = redisTemplate.opsForValue();
//        LinkedList<User> list = new LinkedList<>();
//        list.add(new User("icetea", 18));
//        list.add(new User("icetea-1", 28));
//        valueOperations.set(key, list);
//        // get
//        List<User> users = valueOperations.get(key);
//        return users;
//    }

//    @Test
//    public void testStringSetKey() {
//        String key = "icetea:testobj";
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap();
//        linkedHashMap.put("username", "icetea");
//        linkedHashMap.put("age", 18);
//        // 存
//        valueOperations.set(key, linkedHashMap);
//
//        // 取出来是linkedHashMap类型
//        Object o = valueOperations.get(key);
//        Class<?> aClass = o.getClass();
//        log.info("classType = {}", aClass.getName());
//    }

//    /**
//     * 存字符串
//     */
//    @RequestMapping("create-str")
//    public void testCreateString() {
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap();
//        linkedHashMap.put("username", "icetea");
//        linkedHashMap.put("age", 18);
//        String s = JSON.toJSONString(linkedHashMap);
//        valueOperations.set("icetea:teststr", s);
//
//        // 取出来是string类型
//        Object o = valueOperations.get("icetea:teststr");
//        Class<?> aClass = o.getClass();
//        log.info("classType = {}", aClass.getName());
//    }
//

//
//    public static void main(String[] args) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap();
//        linkedHashMap.put("username", "icetea");
//        linkedHashMap.put("age", 18);
//        byte[] bytes = mapper.writeValueAsBytes(linkedHashMap);
//        System.out.println(new String(bytes));
//    }
}
