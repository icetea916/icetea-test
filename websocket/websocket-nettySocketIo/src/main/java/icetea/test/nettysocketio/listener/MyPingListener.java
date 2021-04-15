package icetea.test.nettysocketio.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.PingListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyPingListener implements PingListener {

    @Override
    public void onPing(SocketIOClient client) {
        log.info("heartbeat sessionId={}", client.getSessionId().toString());
    }
}
