package life.icetea.test.nettysocketio.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import life.icetea.test.nettysocketio.domain.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyEventListener {

    private SocketIOServer socketIOServer;

    /**
     * 监听客户端连接
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        log.info("连接成功: sissionId={}", client.getSessionId());
        log.info("client nameSpaces={}, rooms={}", client.getNamespace().getName(), client.getAllRooms());
    }

    /**
     * 监听客户端断开连接
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("断开连接,sessonId={}", client.getSessionId());
    }

    /**
     * 处理消息事件
     * 注意：该框架最好使用string接受参数，如果使用map或这model类框架使用jackson进行解析会对不同平台的客户端参数存在问题，例如js客户端可以但是Java和ios客户端不可以，
     */
    @OnEvent("message-map")
    public void onMessage(SocketIOClient client, AckRequest ackRequest, Map<String, Object> message) {
        log.info("接受消息 message: sessionId={}, message={}", client.getSessionId(), message.toString());
        // 返回消息
        client.sendEvent("message", "已收到发送的消息");
    }

    /**
     * 处理消息事件-model,
     * 注意: 自定义参数顺序要和客户端传来的顺序一致,该框架会自动jackson序列化,如果不一致会报错
     */
    @OnEvent("message")
    public void onMessage(SocketIOClient client, MyMessage message) {
        log.info("接收消息: sessionId={}, message={}", client.getSessionId(), message.toString());
        // 返回消息
        client.sendEvent("message", "已收到发送的消息");
    }

    public MyEventListener(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

}

