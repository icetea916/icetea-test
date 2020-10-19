package icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SocketIOService {

    // 用来存已连接的客户端
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();
    // 推送的事件
    public static final String PUSH_EVENT = "push_event";

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * 监听客户端连接
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String loginUserNum = getParamsByClient(client);
        if (loginUserNum != null) {
            clientMap.put(loginUserNum, client);
        }
    }

    /**
     * 监听客户端断开连接
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String loginUserNum = getParamsByClient(client);
        if (loginUserNum != null) {
            clientMap.remove(loginUserNum);
        }
        client.disconnect();
        System.out.println("断开连接,sessonId=" + client.getSessionId());
    }

    /**
     * 处理消息事件
     */
    @OnEvent("message")
    public void message(SocketIOClient client) {
        String sessionId = client.getSessionId().toString();
        System.out.println("sessionId=[" + sessionId + "]触发message时间");
    }

    @PostConstruct
    public void start() {
        // 添加心跳监听
        socketIOServer.addPingListener(client ->
                System.out.println("heartbeat sessionId=" + client.getSessionId().toString())
        );
        // 处理自定义的事件，与连接监听类似
        socketIOServer.addEventListener(PUSH_EVENT, PushMessage.class, (client, data, ackSender) -> {
            // TODO do something
        });
        socketIOServer.start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     */
    @PreDestroy
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    public void pushMessageToUser(PushMessage pushMessage) {
        String loginUserNum = pushMessage.getLoginUserNum().toString();
        if (loginUserNum != null && !"".equals(loginUserNum)) {
            SocketIOClient client = clientMap.get(loginUserNum);
            if (client != null)
                client.getAllRooms();
                client.sendEvent(PUSH_EVENT, pushMessage);
        }
    }

    /**
     * 此方法为获取client连接中的参数，可根据需求更改
     *
     * @param client
     * @return
     */
    private String getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数（这里的loginUserNum必须是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> list = params.get("loginUserNum");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}

