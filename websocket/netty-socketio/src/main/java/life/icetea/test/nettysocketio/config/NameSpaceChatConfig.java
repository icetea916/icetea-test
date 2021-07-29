package life.icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import life.icetea.test.nettysocketio.listener.ChatNameSpaceEventListener;
import life.icetea.test.nettysocketio.listener.MyPingListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 聊天室命名空间config
 *
 * @author icetea
 */
@Configuration
public class NameSpaceChatConfig {

    /**
     * 聊天命名空间名称
     */
    public static final String NAME_SPACE = "/chat";

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
        // 创建聊天命名空间
        SocketIONamespace socketIONamespace = server.addNamespace(NAME_SPACE);
        socketIONamespace.addListeners(new ChatNameSpaceEventListener(server));
        socketIONamespace.addPingListener(new MyPingListener());
        return socketIONamespace;
    }

}
