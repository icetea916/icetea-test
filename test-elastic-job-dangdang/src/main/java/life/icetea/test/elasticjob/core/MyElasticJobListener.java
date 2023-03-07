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

    private long beginTime = 0;

    /**
     * 任务开始是调用
     */
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        beginTime = System.currentTimeMillis();
        log.info("===>{} 任务执行开始 timestamp={} <===", shardingContexts.getJobName(), beginTime);
    }

    /**
     * 当前服务器上的所有任务分片执行完毕后调用
     *
     * @param shardingContexts
     */
    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        log.info("{} 任务执行结束 timestamp={}, 总耗时={}秒 <===", shardingContexts.getJobName(), endTime, (endTime - beginTime) / 1000);
    }

}