package life.icetea.test.nettysocketio.serverdemo;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import life.icetea.test.nettysocketio.domain.ChatMessage;

import java.io.InputStream;

public class SslChatLauncher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(10443);

        config.setKeyStorePassword("test1234");
        InputStream stream = SslChatLauncher.class.getResourceAsStream("/keystore.jks");
        config.setKeyStore(stream);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("message", ChatMessage.class, new DataListener<ChatMessage>() {
            @Override
            public void onData(SocketIOClient client, ChatMessage data, AckRequest ackRequest) {
                server.getBroadcastOperations().sendEvent("message", data);
            }
        });

        server.start();
    }

}
