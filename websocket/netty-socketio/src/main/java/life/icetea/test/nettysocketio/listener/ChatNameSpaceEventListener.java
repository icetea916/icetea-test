package life.icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import life.icetea.test.nettysocketio.config.NameSpaceChatConfig;
import life.icetea.test.nettysocketio.domain.Message;
import life.icetea.test.nettysocketio.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * chat namespace 事件监听器
 *
 * @author icetea
 */
@Slf4j
public class ChatNameSpaceEventListener {


    /**
     * 发送消息事件名称
     */
    private static final String EVENT_MESSAGE = "message";

    /**
     * socket io服务
     */
    private SocketIOServer server;
    /**
     * 命名空间
     */
    private SocketIONamespace namespace;

    /**
     * 监听客户端连接
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        HandshakeData handshakeData = client.getHandshakeData();
        String username = handshakeData.getSingleUrlParam("username");
        String age = handshakeData.getSingleUrlParam("age");
        BroadcastOperations broadcastOperations = namespace.getBroadcastOperations();
        broadcastOperations.sendEvent(EVENT_MESSAGE, new Message<String>(new Date(), username, username + "用户已上线"));

        // 保存用户数据至client中
        User user = new User();
        user.setUsername(username);
        user.setAge(Integer.valueOf(age));
        client.set("user", user);

        log.info("连接成功: username={},namespace={},sessionId={}", username, client.getNamespace().getName(), client.getSessionId());
    }

    /**
     * 监听客户端断开连接
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        HandshakeData handshakeData = client.getHandshakeData();
        String username = handshakeData.getSingleUrlParam("username");
        BroadcastOperations broadcastOperations = namespace.getBroadcastOperations();
        broadcastOperations.sendEvent(EVENT_MESSAGE, new Message<String>(new Date(), username, username + "用户已下线"));
        log.info("断开连接, username={},sessionId={}", username, client.getSessionId());
    }

    /**
     * 处理消息事
     */
    @OnEvent(EVENT_MESSAGE)
    public void onSendMessage(SocketIOClient client, AckRequest ackRequest, Message message) {
        log.info("接受消息: message={}, sessionId={}", message, client.getSessionId());
        // 广播消息
        BroadcastOperations broadcastOperations = namespace.getBroadcastOperations();
        broadcastOperations.sendEvent(EVENT_MESSAGE, message);
    }

    public ChatNameSpaceEventListener(SocketIOServer server) {
        this.server = server;
        this.namespace = server.getNamespace(NameSpaceChatConfig.NAME_SPACE);
    }

}

