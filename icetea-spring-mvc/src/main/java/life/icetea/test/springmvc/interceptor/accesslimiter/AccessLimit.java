package life.icetea.test.springmvc.interceptor.accesslimiter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口流量限制注解
 *
 * @author icetea
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {

    /**
     * 每seconds秒內
     */
    int seconds() default 5;

    /**
     * 每seconds秒内限流多少次
     */
    int maxCount() default 5;
}
