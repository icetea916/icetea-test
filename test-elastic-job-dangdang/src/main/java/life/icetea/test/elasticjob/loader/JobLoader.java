package life.icetea.test.elasticjob.loader;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import life.icetea.test.elasticjob.listener.MyElasticJobListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * @author icetea
 */
@Slf4j
public class JobLoader implements CommandLineRunner, ApplicationContextAware, EmbeddedValueResolverAware {

    @Autowired
    private ZookeeperRegistryCenter regCenter;
    private StringValueResolver resolver;
    public ConfigurableApplicationContext applicationContext;

    @Override
    public void run(String... strings) throws Exception {
//        log.info("加载job开始......");
//        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(ZjtxSchedule.class);
//        for (Map.Entry e : beansWithAnnotation.entrySet()) {
//            if (!(e.getValue() instanceof ElasticJob)) {
//                continue;
//            }
//            // 获取注解属性值
//            ZjtxSchedule annotation = e.getValue().getClass().getAnnotation(ZjtxSchedule.class);
//            String cron = resolver.resolveStringValue(annotation.cron());
//            String jobName = resolver.resolveStringValue(annotation.jobName());
//            String description = resolver.resolveStringValue(annotation.discreption());
//            String shardingTotalCount = resolver.resolveStringValue(annotation.shardingNumber());
//            String shardingItemParameters = resolver.resolveStringValue(annotation.shardingParams());
//
//            // 创建job实例
//            initJob((ElasticJob) e.getValue(), cron, jobName, description, Integer.valueOf(shardingTotalCount), shardingItemParameters);
//
//        }
//        log.info("加载job结束");
    }

    /**
     * 初始化job
     */
    private void initJob(ElasticJob job, String cron, String jobName, String description, Integer shardingTotalCount, String shardingItemParameters) {
        MyElasticJobListener jobListener = new MyElasticJobListener();

        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration
                .newBuilder(jobName, cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters)
                .description(description)
                .failover(true)
                .misfire(false)
                .build();

        JobTypeConfiguration jobTypeConfiguration = null;
        if (job instanceof SimpleJob) {
            // 简单类型job
            jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, job.getClass().getCanonicalName());
        } else {
            // 数据流类型job
            jobTypeConfiguration = new DataflowJobConfiguration(jobCoreConfiguration, job.getClass().getCanonicalName(), true);
        }

        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(jobTypeConfiguration)
                .overwrite(true)
                .build();
        new SpringJobScheduler(job, regCenter, liteJobConfiguration, jobListener)
                .init();

        log.info("\n加载定时任务完成......\n"
                + "名称: " + jobName + "\n"
                + "描述: " + description + "\n"
                + "cron表达式: " + cron + "\n"
                + "分片总数量: " + shardingTotalCount + "\n"
                + "分片参数: " + shardingItemParameters + "\n"
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.resolver = stringValueResolver;
    }

}
