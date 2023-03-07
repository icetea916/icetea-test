package life.icetea.test.elasticjob.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置zookeeper注册中心
 */
@Configuration
public class ElasticRegCenterConfig {

    private final String serverList = "localhost:2181";

    private final String namespace = "icetea-job";

    /**
     * 连接Zookeeper的权限令牌.
     * 缺省为不需要权限验证.
     */
//    private final String digest = "icetea:iceteaZookeeper";

    /**
     * 配置zookeeper
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter() {
        ZookeeperConfiguration configuration = new ZookeeperConfiguration(serverList, namespace);
//        configuration.setDigest(digest);
        return new ZookeeperRegistryCenter(configuration);
    }

}