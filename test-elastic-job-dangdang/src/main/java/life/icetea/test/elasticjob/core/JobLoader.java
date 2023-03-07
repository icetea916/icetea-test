package life.icetea.test.elasticjob.core;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.util.Map;

/**
 * @author icetea
 */
@Slf4j
@Component
public class JobLoader implements CommandLineRunner, ApplicationContextAware, EmbeddedValueResolverAware {

    @Autowired
    private ZookeeperRegistryCenter regCenter;
    private StringValueResolver resolver;
    public ConfigurableApplicationContext applicationContext;

    @Override
    public void run(String... strings) throws Exception {
        log.info("加载job开始......");
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Job.class);
        for (Map.Entry e : beansWithAnnotation.entrySet()) {
            // 创建job core 属性
            Job annotation = e.getValue().getClass().getAnnotation(Job.class);
            String cron = resolver.resolveStringValue(annotation.cron());
            String name = resolver.resolveStringValue(annotation.name());
            String description = resolver.resolveStringValue(annotation.description());
            String shardingItemParameters = resolver.resolveStringValue(annotation.shardingItemParameters());
            JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration
                    .newBuilder(name, cron, annotation.shardingTotalCount())
                    .shardingItemParameters(shardingItemParameters)
                    .description(description)
                    .failover(true)
                    .misfire(false)
                    .build();

            // 创建job实例
            initJob((ElasticJob) e.getValue(), jobCoreConfiguration);
        }
        log.info("加载job结束");
    }

    /**
     * 初始化job
     */
    private void initJob(ElasticJob job, JobCoreConfiguration jobCoreConfiguration) {
        JobTypeConfiguration jobTypeConfiguration = null;
        if (job instanceof SimpleJob) {
            // 简单类型job
            jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, job.getClass().getCanonicalName());
            jobCoreConfiguration.getJobProperties();
        } else {
            // 数据流类型job
            jobTypeConfiguration = new DataflowJobConfiguration(jobCoreConfiguration, job.getClass().getCanonicalName(), true);
        }

        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(jobTypeConfiguration)
                .overwrite(true)
                .build();

        new SpringJobScheduler(job, regCenter, liteJobConfiguration, new MyElasticJobListener())
                .init();

        log.info("\n加载定时任务完成......\n"
                + "名称: " + jobCoreConfiguration.getJobName() + "\n"
                + "描述: " + jobCoreConfiguration.getDescription() + "\n"
                + "cron表达式: " + jobCoreConfiguration.getCron() + "\n"
                + "分片总数量: " + jobCoreConfiguration.getShardingTotalCount() + "\n"
                + "分片参数: " + jobCoreConfiguration.getShardingItemParameters() + "\n"
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
