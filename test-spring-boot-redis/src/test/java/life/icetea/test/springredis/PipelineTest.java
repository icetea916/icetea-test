package life.icetea.test.springredis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author icetea
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PipelineTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test01() {
        Object result = stringRedisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            // set
            for (int i = 0; i < 3; i++) {
                connection.set(String.format("icetea:%d", i).getBytes(), "icetea".getBytes());
            }

            // get
            for (int i = 0; i < 3; i++) {
                connection.get(String.format("icetea:%d", i).getBytes());
            }

            return null;
        });
        System.out.println(result);
    }

}
