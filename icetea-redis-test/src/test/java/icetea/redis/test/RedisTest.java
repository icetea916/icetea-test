package icetea.redis.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        Long increment = redisTemplate.opsForValue().increment("my-test", 1);
        redisTemplate.expire("my-test", 5, TimeUnit.SECONDS);
        log.info("i={}", increment);
    }

}
