package life.icetea.nettysocketio.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import life.icetea.nettysocketio.domain.MyMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * chat namespace 监听器
 *
 * @author icetea
 */
@Slf4j
public class ChatNameSpaceListener {

    private SocketIOServer server;

    /**
     * 监听客户端连接
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        server.getAllClients();
        // 从请求的连接中拿出参数（这里的loginUserName必须是唯一标识）
        String loginUserName = client.getHandshakeData().getSingleUrlParam("loginUserName");
        log.info("client nameSpaces = {}", client.getNamespace().getName());
        log.info("client rooms = {}", client.getAllRooms());
        log.info("连接成功:sissionId={},loginUsername={} connected", client.getSessionId(), loginUserName);
    }

    /**
     * 监听客户端断开连接
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String loginUserNum = client.getHandshakeData().getSingleUrlParam("loginUserName");
        client.disconnect();
        log.info("断开连接,sessonId={}, loginUsername={}", client.getSessionId(), loginUserNum);
    }

    /**
     * 处理消息事
     */
    @OnEvent("message")
    public void onSendMessage(SocketIOClient client, AckRequest ackRequest, MyMessage message) {
        log.info("接受消息model类详细自动转换 message: sessionId={}, message={}", client.getSessionId(), message);
        // 返回消息
        BroadcastOperations broadcastOperations = client.getNamespace().getBroadcastOperations();
        broadcastOperations.sendEvent("message", client.get("username"), message);
    }

    public ChatNameSpaceListener(SocketIOServer server) {
        this.server = server;
    }

}

