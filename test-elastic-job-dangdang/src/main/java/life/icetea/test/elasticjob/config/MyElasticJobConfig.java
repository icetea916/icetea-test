package life.icetea.test.elasticjob.config;

import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import life.icetea.test.elasticjob.listener.MyElasticJobListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author icetea
 */
@Configuration
@PropertySource(value = "classpath:job.properties", encoding = "UTF-8")
public class MyElasticJobConfig {

    /**
     * 配置任务监听器
     */
    @Bean
    public ElasticJobListener elasticJobListener() {
        return new MyElasticJobListener();
    }

}