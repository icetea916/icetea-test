package life.icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import life.icetea.test.nettysocketio.listener.NameSpaceEventListener;
import life.icetea.test.nettysocketio.listener.MyPingListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 命名空间config
 *
 * @author icetea
 */
@Configuration
public class NameSpaceConfig {

    /**
     * 命名空间名称
     */
    public static final String NAME_SPACE = "namespace";

    /**
     * socketio服务
     */
    @Autowired
    private SocketIOServer server;

    /**
     * 聊天 chat namespace
     */
    @Bean
    public SocketIONamespace chatNameSpace() {
        // 创建命名空间
        SocketIONamespace socketIONamespace = server.addNamespace(NAME_SPACE);
        socketIONamespace.addListeners(new NameSpaceEventListener(server));
//        socketIONamespace.addPingListener(new MyPingListener());
        return socketIONamespace;
    }

}
