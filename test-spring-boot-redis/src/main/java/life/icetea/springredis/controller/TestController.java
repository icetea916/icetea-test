package life.icetea.springredis.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author icetea
 */
@RequestMapping("test")
@Controller
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存obj
     */
    @RequestMapping("create-obj")
    public void test1() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("username", "icetea");
        linkedHashMap.put("age", 18);
        valueOperations.set("icetea:testobj", linkedHashMap);
    }

    /**
     * 存字符串
     */
    @RequestMapping("create-str")
    public void testCreateString() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("username", "icetea");
        linkedHashMap.put("age", 18);
        String s = JSON.toJSONString(linkedHashMap);
        valueOperations.set("icetea:teststr", s);
        System.out.println(s);
    }

    /**
     * 取
     */
    @RequestMapping("get-obj")
    public void testGetObj() {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get("icetea:testobj");
        System.out.println(o);
    }

    /**
     * 使用GenericJackson2JsonRedisSerializer序列化数据
     */
    @RequestMapping("create-by-gj")
    @ResponseBody
    public Object testGenericJackson2JsonRedisSerializer() {
        String key = "icetea:testgj";
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        User user = new User();
        user.setUsername("icetea");
        user.setAge(18);
        valueOperations.set(key, user);
        // get
        User user1 = valueOperations.get(key);
        return user1;
    }

    /**
     * 使用GenericJackson2JsonRedisSerializer序列化数据 数组
     */
    @RequestMapping("create-by-gj-with-array")
    @ResponseBody
    public Object testGenericJackson2JsonRedisSerializerWithArray() {
        String key = "icetea:testgj:array";
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        ValueOperations<String, List<User>> valueOperations = redisTemplate.opsForValue();
        LinkedList<User> list = new LinkedList<>();
        list.add(new User("icetea", 18));
        list.add(new User("icetea-1", 28));
        valueOperations.set(key, list);
        // get
        List<User> users = valueOperations.get(key);
        return users;
    }

    @ResponseBody
    @RequestMapping("get-by-key")
    public Object getByKey(String key) {
        ShardedJedisPool pool = null;
        // 配置Redis信息
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        // 集群
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("127.0.0.1", 6379);

        // 设置Redis的密码
        jedisShardInfo1.setPassword("123456");

        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);

        ShardedJedis jedis = pool.getResource();
        return jedis.get(key);
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("username", "icetea");
        linkedHashMap.put("age", 18);
        byte[] bytes = mapper.writeValueAsBytes(linkedHashMap);
        System.out.println(new java.lang.String(bytes));
    }
}
