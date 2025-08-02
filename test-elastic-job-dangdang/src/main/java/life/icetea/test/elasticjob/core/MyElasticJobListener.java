package life.icetea.test.elasticjob.core;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;

/**
 * job监听器
 *
 * @author icetea
 */
@Slf4j
public class MyElasticJobListener implements ElasticJobListener {

    private final ThreadLocal<Long> beginTimeThreadLocal = new ThreadLocal<>();

    /**
     * 任务开始时调用
     */
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        long beginTime = System.currentTimeMillis();
        beginTimeThreadLocal.set(beginTime);
        log.info("任务开始: jobName={} beginTime={} ", shardingContexts.getJobName(), beginTime);
    }

    /**
     * 任务结束调用
     * 注意： 当前服务器上的所有任务分片执行完毕后调用
     *
     * @param shardingContexts
     */
    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        Long beginTime = beginTimeThreadLocal.get();
        log.info("任务结束：jobName={} 总耗时={}秒, endtime={}ms, beginTime={}ms", shardingContexts.getJobName(), (endTime - beginTime) / 1000, beginTime, endTime);
        beginTimeThreadLocal.remove();
    }

}