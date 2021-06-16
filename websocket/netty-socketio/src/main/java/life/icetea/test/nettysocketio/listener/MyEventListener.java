package life.icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import life.icetea.test.nettysocketio.domain.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MyEventListener {

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * 监听客户端连接
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        socketIOServer.getAllClients();
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
     * 处理消息事件
     */
    @OnEvent("message-map")
    public void onSendMessage(SocketIOClient client, AckRequest ackRequest, Map<String, Object> message) {
        log.info("接受消息 message: sessionId={}, message={}", client.getSessionId(), message.toString());
        // 返回消息
        client.sendEvent("message", "已收到发送的消息");
    }

    /**
     * 处理消息事件-model,
     * 注意: 自定义参数顺序要和客户端传来的顺序一致,该框架会自动jackson序列化,如果不一致会报错
     */
    @OnEvent("message-model")
    public void onSendMessage(SocketIOClient client, AckRequest ackRequest, MyMessage message, String strMessage) {
        log.info("接受消息model类详细自动转换 message: sessionId={}, message={},{}", client.getSessionId(), message.toString(), strMessage);
        // 返回消息
        client.sendEvent("message", "已收到发送的消息");
    }

}

