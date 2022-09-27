package life.icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.listener.PingListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyPingListener implements PingListener {

    /**
     * ping 客户端
     *
     * @param client
     */
    @Override
    public void onPing(SocketIOClient client) {
        String namespace = client.getNamespace().getName();
        log.info("ping sessionId={}, namespace={}", client.getSessionId().toString(), namespace);
    }

}
