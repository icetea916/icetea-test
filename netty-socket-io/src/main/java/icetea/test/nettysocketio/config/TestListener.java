package icetea.test.nettysocketio.config;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

//@Component
public class TestListener {

    @OnConnect
    public void onConnect(SocketIOClient client) {
        client.sendEvent("message", 200, 200);
        String token = client.getHandshakeData().getSingleUrlParam("token");
        String newsessionId1 = client.getSessionId().toString();
        System.out.println("token=" + token);
        System.out.println("sessionId=" + newsessionId1);
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("sessonId=[" + client.getSessionId() + "]断开连接");
    }

    /**
     * 事件
     */
    @OnEvent("message")
    public void message(SocketIOClient client) {
        String sessionId = client.getSessionId().toString();
        System.out.println("sessionId=[" + sessionId + "]触发message时间");
    }

}