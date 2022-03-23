package life.icetea.test.springmvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 限流拦截器
 */
//@Component
@Slf4j
public class RateLimiterInterceptor extends HandlerInterceptorAdapter {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Integer> valueOps;
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Integer> redisTemplate;

    private final int LIMIT_TIME = 3;// 每3秒允许访问
    private final int LIMIT_NUMBER = 3;// 3次同一个方法

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(getClass().getName() + "intercepting");
        String ip = request.getHeader("X-Forwarded-For");
        String method = request.getMethod();
        String name = request.getRequestURI();
        String key = "rate:limit:" + ip + method + name;
        long currentNumber = valueOps.increment(key, 1);
        log.debug("rate limit key is {}, current number is {}", key, currentNumber);
        if (currentNumber == 1) {// 从第一次访问设置过期时间
            redisTemplate.expire(key, LIMIT_TIME, TimeUnit.SECONDS);
        }
        if (currentNumber > LIMIT_NUMBER) {
            log.info("{} request {} times touch LIMIT_NUMBER ({})", key, currentNumber, LIMIT_NUMBER);
            throw new RuntimeException("REQUEST_FREQUENTLY 访问频繁！");
        }

        return super.preHandle(request, response, handler);
    }
}
