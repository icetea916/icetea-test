package life.icetea.test.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import life.icetea.test.elasticjob.core.Job;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * simple demo
 *
 * @author icetea
 */
@Job(
        cron = "0/10 * * * * ?",
        name = "simpleJobDemo",
        shardingTotalCount = 4,
        shardingItemParameters = "0=icetea-1,1=2,2=3,3=4"
)
@Slf4j
public class SimpleJobDemo implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("任务开始：任务名称={}, 当前任务参数={}, 任务总片数={}, 当前分片项={}, 当前分片参数={}, ",
                shardingContext.getJobName(),
                shardingContext.getJobParameter(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter()
        );

        String shardingParameter = shardingContext.getShardingParameter();
        long sleepTime = 1000;
        switch (shardingParameter) {
            case "1":
                sleepTime = 1000;
                break;
            case "2":
                sleepTime = 2000;
                break;
            case "3":
                sleepTime = 3000;
                break;
            case "4":
                sleepTime = 15000;
//                throw new RuntimeException("test error");
                break;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error("运行失败", e);
        }
    }

}