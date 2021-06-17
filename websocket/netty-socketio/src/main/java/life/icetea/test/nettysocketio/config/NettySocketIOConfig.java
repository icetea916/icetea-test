package life.icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import life.icetea.test.nettysocketio.listener.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * netty socketio config
 * <p>
 * github:
 *
 * @author icetea
 */
@Configuration
@Slf4j
public class NettySocketIOConfig {

    private static final Integer port = 9099;

    @Bean
    public SocketIOServer socketIOServer() throws Exception {
        // 创建socket.io配置
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

        // tcp socket config
        SocketConfig socketConfig = new SocketConfig();
        // 多个tcp socket可以复用一个地址
        socketConfig.setReuseAddress(true);
        config.setSocketConfig(socketConfig);

//        config.setHostname();
        config.setPort(port);
        // 设置url链接前缀，默认=/socket.io
        config.setContext("/socket.io");
        // 设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
        config.setMaxFramePayloadLength(1024 * 1024);
        // 设置http交互最大内容长度
        config.setMaxHttpContentLength(1024 * 1024);
        // 协议升级超时时间（毫秒）即HTTP握手升级为ws协议超时时间，默认10秒。
        config.setUpgradeTimeout(10000);
        // 配置异常监听器
        config.setExceptionListener(new MyExceptionListener());
        // 配置权限监听器
        config.setAuthorizationListener(new MyAuthorizationListener());
        // boss线程数量，0 = current_processors_amount * 2
        config.setBossThreads(1);
        // worker线程数量，0 = current_processors_amount * 2
        config.setWorkerThreads(10);
        config.setAllowCustomRequests(true);
        // 心跳Ping消息超时时间（毫秒),默认60秒,当超过这个时间没有接收到心跳消息就会断开连接,
        // 例如： 心跳间隔为5s，心跳超时未2s，在收到一次心跳开始计时，应在第5s的时候收到下一次心跳，但如果没有收到，server会再等2s，如果还没有收到则会判定心跳超时，断开该客户端连接
        config.setPingTimeout(2000);
        // 心跳Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(3000);

        // 创建socketIO server
        SocketIOServer server = new SocketIOServer(config);
        // 添加心跳监听，该方法是对所有namespace添加心跳监听，也可对单个namespace添加, 如下有例子
        server.addPingListener(new MyPingListener());
        // 添加监听器
        server.addListeners(new MyEventListener(server));

        // 开启socket服务
        server.start();
        return server;
    }

    /**
     * 聊天 chat namespace
     */
    @Bean
    public SocketIONamespace chatNameSpace(SocketIOServer server) {
        // 创建聊天命名空间
        SocketIONamespace socketIONamespace = server.addNamespace("/chat");
        socketIONamespace.addListeners(new ChatNameSpaceListener(server));
        socketIONamespace.addPingListener(new MyPingListener());

        return socketIONamespace;
    }


    /**
     * 配置spring注解扫描器
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

}