package life.icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import life.icetea.test.nettysocketio.domain.Message;
import life.icetea.test.nettysocketio.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * namespace 事件监听器
 *
 * @author icetea
 */
@Slf4j
public class NameSpaceEventListener {

    /**
     * 发送消息事件名称
     */
    private static final String EVENT_MESSAGE = "message";
    /**
     * 当前namespace在线人数
     */
    private static AtomicLong num = new AtomicLong(0);
    /**
     * 命名空间对象
     */
    private SocketIONamespace namespace;

    /**
     * 监听客户端连接
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        HandshakeData handshakeData = client.getHandshakeData();
        String username = handshakeData.getSingleUrlParam("username");
        BroadcastOperations broadcastOperations = namespace.getBroadcastOperations();
        broadcastOperations.sendEvent(EVENT_MESSAGE, new Message<String>(new Date(), username, username + "用户已上线"));

        // 保存用户数据至client中
        User user = new User();
        user.setUsername(username);
        client.set("user", user);

        log.info("namespace={}连接成功: username={},sessionId={}", client.getNamespace().getName(), username, client.getSessionId());
        num.incrementAndGet();
        log.info("当前namespace在线人数: num={}", num);
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

        User user = (User) client.get("user");
        log.info("namespace={}断开连接: username={},sessionId={}", client.getNamespace().getName(), user.getUsername(), client.getSessionId());
        num.decrementAndGet();
    }

    /**
     * 处理消息
     */
    @OnEvent(EVENT_MESSAGE)
    public void onSendMessage(SocketIOClient client, AckRequest ackRequest, Message message) {
        log.info("接受消息: message={}, sessionId={}", message, client.getSessionId());
        // 广播消息
        BroadcastOperations broadcastOperations = namespace.getBroadcastOperations();
        broadcastOperations.sendEvent(EVENT_MESSAGE, message);
    }

    public NameSpaceEventListener(SocketIONamespace namespace) {
        this.namespace = namespace;
    }

}

