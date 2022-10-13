package life.icetea.test.nettysocketio.client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.Polling;
import io.socket.engineio.client.transports.WebSocket;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * socket io client 连接namespace demo
 * <p>
 * 使用socketio client 1.0.1版本
 * 文档：https://socketio.github.io/socket.io-client-java/installation.html
 * github： https://github.com/socketio/socket.io-client-java
 *
 * @author icetea
 */
@Slf4j
public class SocketIOClientWithNamespace {

    public static String server_url = "http://127.0.0.1:9099/chat";

    public static void main(String args[]) throws URISyntaxException, InterruptedException {
        URI uri = URI.create(server_url);

        // client连接参数设置
        IO.Options options = new IO.Options();

        // IO factory options
        options.forceNew = true;
        options.multiplex = true;

        // low-level engin options
        options.transports = new String[]{WebSocket.NAME, Polling.NAME}; // 设定传输方式列表选择, 系统会从中选择最优的方式
        options.upgrade = true;
        options.rememberUpgrade = true;
        options.path = "/socket.io"; // 默认socket.io
        options.query = "username=icetea";

        // Manager options
        options.reconnection = true; // 是否进行重连,默认true
        options.reconnectionDelay = 1_000; // 重连间隔时间 单位ms
        options.reconnectionDelayMax = 5_000; // 最大重连间时间,即一直连不上的最大时间 单位ms
        options.reconnectionAttempts = Integer.MAX_VALUE;  // 重新连接重试次数
        options.timeout = 20_000; // 连接超时时间 ms

        // client创建
        Socket socket = IO.socket(uri, options);
        // 事件绑定
        socket.on(Socket.EVENT_CONNECTING, (data) -> log.info("正在连接 connecting"))
                .on(Socket.EVENT_RECONNECTING, (data) -> log.error("重新连接 reconnect"))
                .on(Socket.EVENT_RECONNECT_ERROR, (data) -> log.error("重新连接出错 reconnect error"))
                .on(Socket.EVENT_RECONNECT_FAILED, (data) -> log.error("重新连接失败 reconnect fail"))
                .on(Socket.EVENT_CONNECT_TIMEOUT, (data) -> log.info("连接超时 connect timeout"))
                .on(Socket.EVENT_PING, (data) -> log.info("心跳 ping"))
                .on(Socket.EVENT_PONG, (data) -> log.info(data[0].toString()))
                .on(Socket.EVENT_CONNECT, (data) -> log.info("连接成功 connect"))
                .on(Socket.EVENT_DISCONNECT, (data) -> { log.info("断开连接 disconnect");})
                .on(Socket.EVENT_CONNECT_ERROR, (exception) -> {
                    // 异常 = EngineIOException
                    Exception e = (Exception) exception[0];
                    log.error("连接出错 connect error", e);
                });

        // 绑定接收消息事件（自定义）
        socket.on("message", (data) -> log.info("接收消息： message={}", data.toString()));

        // 连接
        socket.open();
    }

}
