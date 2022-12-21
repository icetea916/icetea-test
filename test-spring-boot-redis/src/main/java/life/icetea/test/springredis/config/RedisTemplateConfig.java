package life.icetea.test.springredis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <a href="https://www.iocoder.cn/Spring-Boot/Redis/">参考资料</a>
 */
@Configuration
public class RedisTemplateConfig {

    /**
     * 自定义配置redisTemplate
     *
     * @param connectionFactory redis连接池
     */
    //    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        // 设置connection factory
        redisTemplate.setConnectionFactory(connectionFactory);
        // 设置key的序列化器, 使用 String 序列化方式，序列化 KEY 。
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // 设置value的序列化器, 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
        RedisSerializer.json(); // GenericJackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        return redisTemplate;
    }

}
