package life.icetea.test.nettysocketio.demo1;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

public class TestServerDemo1 {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9099);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("message", String.class, (client, data, ackRequest) -> {
            server.getBroadcastOperations().sendEvent("message", data);
        });

        server.start();
    }

}
