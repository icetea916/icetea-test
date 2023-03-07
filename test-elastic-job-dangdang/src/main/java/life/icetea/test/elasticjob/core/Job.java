package life.icetea.test.elasticjob.core;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author icetea
 * @date 2023-02-23 14:38
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Job {

    /**
     * 任务名称
     */
    String name();

    /**
     * cron 表达式
     */
    String cron();

    /**
     * 任务描述
     */
    String description() default "";

    /**
     * 任务分片总数
     */
    int shardingTotalCount();

    /**
     * 分片参数
     */
    String shardingItemParameters();

}
