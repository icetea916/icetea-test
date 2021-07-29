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
        log.info("连接成功: sessionId={}", client.getSessionId());

        // 根据token查询用户数据
        User user = new User();
        user.setUsername(handshakeData.getSingleUrlParam("username"));
        user.setAge(Integer.valueOf(handshakeData.getSingleUrlParam("age")));
        // 将user信息保存到client中
        client.set("user", user);

        log.info("client nameSpaces={}, rooms={}", client.getNamespace().getName(), client.getAllRooms());
    }

    /**
     * 监听客户端断开连接
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("断开连接,sessionId={}", client.getSessionId());
    }

    public MyEventListener(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

}

