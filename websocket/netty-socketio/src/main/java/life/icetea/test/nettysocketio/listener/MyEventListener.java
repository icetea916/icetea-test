package life.icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import life.icetea.test.nettysocketio.domain.Message;
import life.icetea.test.nettysocketio.domain.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyEventListener {

    private SocketIOServer socketIOServer;

    /**
     * 处理消息事件
     * <p>
     * 注意:
     * 1. 自定义参数顺序要和客户端传来的顺序,如果不一致会报错
     * 2. java-client传参是不能直接传javaBean，并且不能直接将javaBean转换成json字符串，需要转换为对应的map树才可以，例如使用fastjson构建JSONObject
     */
    @OnEvent("message")
    public void onMessage(SocketIOClient client, Message message) {
        log.info("接收消息: sessionId={}, message={}", client.getSessionId(), message.getContent());
        // 广播消息
        BroadcastOperations broadcastOperations = socketIOServer.getBroadcastOperations();
        User user = client.get("user");
        message.setUsername(user.getUsername());
        broadcastOperations.sendEvent("message", message);
    }

    /**
     * 客户端连接event
     * 1. 查询用户的信息并保存至client中
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        HandshakeData handshakeData = client.getHandshakeData();
        String username = handshakeData.getSingleUrlParam("username");

        // 构造用户并存储到session中
        User user = new User();
        user.setUsername(username);
        client.set("user", user);

        log.info("namespace={}连接成功: username={},sessionId={}", client.getNamespace().getName(), username, client.getSessionId());
    }

    /**
     * 监听客户端断开连接
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        User user = (User) client.get("user");
        log.info("namespace={}断开连接: username={},sessionId={}", client.getNamespace().getName(), user.getUsername(), client.getSessionId());
    }

    public MyEventListener(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

}

