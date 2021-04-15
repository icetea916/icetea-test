package icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import icetea.test.nettysocketio.constant.CommonConstants;
import icetea.test.nettysocketio.listener.MyAuthorizationListener;
import icetea.test.nettysocketio.listener.MyExceptionListener;
import icetea.test.nettysocketio.listener.MyPingListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettySocketIOConfig {

    private static final Integer port = 9099;

    @Bean
    public SocketIOServer socketIOServer() throws Exception {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        config.setSocketConfig(socketConfig);
        config.setPort(port);
        // 设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
        config.setMaxFramePayloadLength(1024 * 1024);
        // 设置http交互最大内容长度
        config.setMaxHttpContentLength(1024 * 1024);
        // 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(10000);
        config.setExceptionListener(new MyExceptionListener());
        config.setAuthorizationListener(new MyAuthorizationListener());
        // socket连接数大小（如只监听一个端口boss线程组为1即可）
        config.setBossThreads(1);
        config.setWorkerThreads(10);
        config.setAllowCustomRequests(true);
        // 心跳Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(3000);
        // 心跳Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(10000);

        // 创建socketIO server
        SocketIOServer server = new SocketIOServer(config);
        // 添加心跳监听
        server.addPingListener(new MyPingListener());
        // 处理自定义的事件，与连接监听类似,也可用@Event注解方式
        server.addEventListener(CommonConstants.EVENT_PUSH, PushMessage.class, (client, data, ackSender) -> {
            // TODO do something
        });

        //创建命名空间
//        SocketIONamespace socketIONamespace = server.addNamespace("/test");
//        socketIONamespace.addListeners(testNameSpaceListener);
//        socketIONamespace.addPingListener(new MyPingListener());
        server.addNamespace("test");
        // 开启socket服务
        server.start();

        return server;
    }

    /**
     * 配置spring注解扫描器
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

}