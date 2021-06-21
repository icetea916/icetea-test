package life.icetea.test.springmvc.interceptor.accesslimiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 接口访问频率限制拦截器，结合redis实现
 *
 * @author icetea
 */
@Component
@Slf4j
public class AccessLimitInterceptor extends HandlerInterceptorAdapter {

    /**
     * redis键前缀
     */
    private static final String KEY_PREFIX = "access-limit";
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if (token != null && !"".equals(token)) {// 只限流用token的接口
            if (handler instanceof HandlerMethod) {
                AccessLimit accessLimit = ((HandlerMethod) handler).getMethodAnnotation(AccessLimit.class);
                if (accessLimit != null) {
                    // 接口限流逻辑
                    int maxCount = accessLimit.maxCount();
                    int seconds = accessLimit.seconds();
                    StringBuilder key = new StringBuilder(KEY_PREFIX).append("-").append(token).append("-").append(request.getRequestURI());
                    Long count = redisTemplate.opsForValue().increment(key, 1);
                    if (count == 1) {
                        // 第一次访问，设置超时时间
                        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
                    }
                    if (count > maxCount) {
                        log.debug("访问接口({})次数过多，每{}秒最大访问次数maxCount={}",
                                request.getRequestURI(),
                                seconds,
                                maxCount);
                        throw new RuntimeException("HIGH_NUMBER_OF_VISITS,访问次数过多");
                    }

                }
            }
        }

        return true;
    }
}
