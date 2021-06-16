package life.icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import life.icetea.test.nettysocketio.constant.CommonConstants;
import life.icetea.test.nettysocketio.domain.PushMessage;
import life.icetea.test.nettysocketio.listener.ChatNameSpaceListener;
import life.icetea.test.nettysocketio.listener.MyAuthorizationListener;
import life.icetea.test.nettysocketio.listener.MyExceptionListener;
import life.icetea.test.nettysocketio.listener.MyPingListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * netty socketio config
 *
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
        // 心跳Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(3000);
        // 心跳Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(10000);

        // 创建socketIO server
        SocketIOServer server = new SocketIOServer(config);
        // 添加心跳监听，该方法是对所有namespace添加心跳监听，也可对单个namespace添加, 如下有例子
        server.addPingListener(new MyPingListener());
        // 处理自定义的事件，与连接监听类似,也可用@Event注解方式
//        server.addEventListener(CommonConstants.EVENT_PUSH, PushMessage.class, (client, data, ackSender) -> {
//        });
        // 处理connect时间，与@OnConnect相同
//        server.addConnectListener((client) ->{});
        // 处理disConnect时间，与@OnDisconnect相同
//        server.addDisconnectListener((client) ->{});
        // 处理disConnect时间，与@OnDisconnect相同
//        server.addDisconnectListener((client) -> {});

        // 创建一个namespace
        SocketIONamespace iceteaNamespace = server.addNamespace("icetea");
        // 为盖namespace添加心跳监听
        iceteaNamespace.addPingListener(client -> {
            log.info("pingListener ,sessionId={}, nameSpace={}", client.getSessionId().toString(), client.getNamespace());
        });
//        iceteaNamespace.addConnectListener(client -> {});
//        iceteaNamespace.addDisconnectListener(client -> {});
        iceteaNamespace.addEventListener(CommonConstants.EVENT_PUSH, PushMessage.class, (client, data, ackSender) -> {
            log.info("eventListener, sessionId={}, namespace={}", client.getSessionId().toString(), client.getNamespace());
        });
//        iceteaNamespace.addListeners();

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