package life.icetea.test.nettysocketio.demo1;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.Polling;
import io.socket.engineio.client.transports.WebSocket;

import java.net.URI;

public class TestClientDemo1 {

    public static void main(String[] args) {
        URI uri = URI.create("http://localhost:9099");

        // client连接参数设置
        IO.Options options = IO.Options.builder()
                // IO factory options
                .setForceNew(false)
                .setMultiplex(true)

                // low-level engine options
                .setTransports(new String[]{WebSocket.NAME, Polling.NAME})
                .setUpgrade(true)
                .setRememberUpgrade(false)
                .setPath("/socket.io")
                .setQuery(null)
                .setExtraHeaders(null)

                // Manager options
                .setReconnection(true)
                .setReconnectionAttempts(Integer.MAX_VALUE)
                .setReconnectionDelay(1_000)
                .setReconnectionDelayMax(5_000)
                .setRandomizationFactor(0.5)
                .setTimeout(20_000)

                // Socket options
                .setAuth(null)
                .build();

        // 创建socket
        Socket socket = IO.socket(uri, options);

        socket.open();
    }

}
