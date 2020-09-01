package icetea.test.webSocket.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component
//@ServerEndpoint("/icetea/test/websocket/{username}")
@Slf4j
public class WebSocketTest1 {

    /**
     * 在线人数
     */
    public static volatile Integer INLINE_NUMBER = 0;
    /**
     * 保存信息
     */
    private static Map<String, WebSocketTest1> clients = new ConcurrentHashMap<String, WebSocketTest1>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        this.username = username;
        this.session = session;
        WebSocketTest1.clients.put(username, this);
        WebSocketTest1.addOnlineNumber();
        log.info("{},已连接成功。", username);
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        WebSocketTest1.subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(message);
        String msg = jsonObject.getString("message");
        if (jsonObject.getString("to").equals("all")) {
            WebSocketTest1.sendMessageTo(msg, jsonObject.getString("to"));
        } else {
            WebSocketTest1.sendMessageToAll(msg);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public static void sendMessageTo(String message, String toUserName) {
        for (WebSocketTest1 item : clients.values()) {
            if (item.username.equals(toUserName))
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public static void sendMessageToAll(String message) {
        for (WebSocketTest1 item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static void addOnlineNumber() {
        WebSocketTest1.INLINE_NUMBER++;
    }

    public static void subOnlineCount() {
        WebSocketTest1.INLINE_NUMBER--;
    }

}
