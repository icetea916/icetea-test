package life.icetea.test.nettysocketio.serverdemo;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.ScannerEngine;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import life.icetea.test.nettysocketio.listener.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * netty socketio spring config
 * <p>
 * 注： 只支持socket.io v2.x 版本
 * <p>
 * github: https://github.com/mrniko/netty-socketio-demo
 *
 * @author icetea
 */
@Configuration
@Slf4j
public class SocketIOServerConfig {

    private static final Integer port = 9099;

    /**
     * 创建socketIOServer服务
     */
    @Bean
    public SocketIOServer socketIOServer() throws Exception {
        // 创建socket.io配置，并进行配置
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        // 在本地测试可以设置为localhost或者本机IP，在Linux服务器跑可换成服务器IP
        config.setHostname("localhost");
        config.setPort(port); // 配置端口
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
        // socket连接数大小（如只监听一个端口boss线程组为1即可）
        // boss线程数量，0 = current_processors_amount * 2
        config.setBossThreads(1);
        // worker线程数量，0 = current_processors_amount * 2
        config.setWorkerThreads(200);
        config.setAllowCustomRequests(true);
        // 心跳Ping消息超时时间（毫秒),默认60秒,当超过这个时间没有接收到心跳消息就会断开连接,
        // 例如：心跳间隔为5s，心跳超时未2s，在收到一次心跳开始计时，应在第5s的时候收到下一次心跳，但如果没有收到，server会再等2s，如果还没有收到则会判定心跳超时，断开该客户端连接
        config.setPingTimeout(10000);
        // 心跳Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(5000);

        // TCP socket配置
        SocketConfig socketConfig = new SocketConfig();
        // 多个tcp socket可以复用一个地址
        socketConfig.setReuseAddress(true);
        config.setSocketConfig(socketConfig);

        // 创建socketIO server
        SocketIOServer server = new SocketIOServer(config);
        // 添加心跳ping Listener，该方法是对所有namespace添加心跳监听，也可对单个namespace添加, 如下有例子
        server.addPingListener(new MyPingListener());
        // 添事件Listener
        server.addListeners(new MyEventListener(server));

        // 添加namespace
        SocketIONamespace chat1Namespace = server.addNamespace("/chat1");
//        chatNamespace.addPingListener(new MyPingListener()); 在namespace添加pingListener无效，不知
        chat1Namespace.addListeners(new NameSpaceEventListener(chat1Namespace));

        // 添加namespace
        SocketIONamespace chat2Namespace = server.addNamespace("/chat2");
        chat2Namespace.addListeners(new NameSpaceEventListener(chat2Namespace));

        // 开启socket服务
        server.start();
        return server;
    }

    /**
     * spring注解扫描器，使用 BeanPostProcessget给socketIOServer添加listener
     * <p>
     * 注：只会将容器中的listener注入到socketIOServer中，即默认的命名空间中
     * 通过源码查看，最终使用的都是{@link ScannerEngine} 来进行添加Listener
     *
     * @param socketIOServer 命名空间的socketIOServer
     */
//    @Bean 不用该方式，使用配置的方式添加Listener
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

}