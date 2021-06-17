package life.icete.test.socketio.cilent;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * socket io client demo 2
 * <p>
 * 请注意nettysocketio支持的socket.io标准的版本 socket.io server version >=3.0,
 * <p>
 * 使用2.0.1版本socketio client
 * 文档：https://socketio.github.io/socket.io-client-java/installation.html
 * github： https://github.com/socketio/socket.io-client-java
 *
 * @author icetea
 */
@Slf4j
public class SocketIOClientDemo2 {

    public static String token = "icetea";
    public static String url = "http://127.0.0.1:9099?token=" + token;

    public static void main(String args[]) throws URISyntaxException, InterruptedException {
        // socket连接参数设置
        IO.Options options = IO.Options.builder()
                // IO factory options
                .setForceNew(false) // 是否创建一个新的manager instance, manager instance负责底层连接逻辑, default: false
//                .setMultiplex(true)

                // low-level engine options
//                .setTransports(new String[]{Polling.NAME, WebSocket.NAME})
                .setUpgrade(true) // 是否将long-polling升级到更好的websocket，default: true
                .setRememberUpgrade(false) // 为true，则如果之前的连接通过websocket，则下一个连接会绕过upgrade直接建立websocket连接
                .setPath("/socket.io") // 用来连接websocket器的路径，必须与server配置的相同，该配置和url中的path不一样，url中的path指的是namespace, default: /socket.io
                .setQuery(null) // query请求信息，可以在server端通过HandSharkData获取param，当client更新该参数时，server端并不会更新，只有当session重新建立的时候才会更新
                .setExtraHeaders(null) // header头信息，可以在server端通过HandSharkData获取header，和query 一样只有当session重新建立时在会更新

                // Manager options
                .setReconnection(true)  // 是否重连,默认true
                .setReconnectionAttempts(2) // 最大重试次数
                .setReconnectionDelay(1_000)    // 重连间隔 单位ms
                .setReconnectionDelayMax(2_000) // 最大重连时间, 即一直连不上的最大时间 单位ms
                .setRandomizationFactor(0.5)
                .setTimeout(20_000) // 连接超时时间 ms

                // Socket options
                .setAuth(null)
                .build();

        // client创建
        Socket socket = IO.socket(url, options);

        // manager 时间绑定
        socket.io().on(Manager.EVENT_OPEN, (data) -> log.info("manager open")) // successful (re)connection
                .on(Manager.EVENT_RECONNECT, (data) -> log.info("manager event reconnect")) // successful reconnection
                .on(Manager.EVENT_RECONNECT_ERROR, (data) -> log.error("manager event reconnect error")) // reconnection failure
                .on(Manager.EVENT_RECONNECT_FAILED, (data) -> log.error("manager event reconnect fail")) // reconnection failure after all attempts
                .on(Manager.EVENT_ERROR, (data) -> log.info("manger event error")) // (re)connection failure or error after a successful connection
                .on(Manager.EVENT_RECONNECT_ATTEMPT, (data) -> log.info("manger event reconnect attempt")) //  	reconnection attempt
                .on(Manager.EVENT_CLOSE, (data) -> log.error("manager event close")) // disconnection
                .on(Manager.EVENT_TRANSPORT, (data) -> log.info("manger event transport"))
                .on(Manager.EVENT_PACKET, (data) -> log.info("manager event packet"));

        // socket事件绑定
        socket.on(Socket.EVENT_CONNECT, (data) -> log.info("连接成功 connect"))
                .on(Socket.EVENT_DISCONNECT, (data) -> {
                    log.info("断开连接 disconnect");
                    System.exit(0);
                })
                .on(Socket.EVENT_CONNECT_ERROR, (exception) -> {
                    // 异常 = EngineIOException
                    Exception e = (Exception) exception[0];
                    log.error("连接出错 connect error", e);
                });

        // 绑定自定义事件
        socket.on("message", (data) -> log.info("自定义发送消息事件 message={}", data.toString()));

        // 连接
        socket.connect();

        Thread.sleep(2000);

        if (socket.connected()) {
            // 发送消息
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println("请输入消息(回车发送):");
                String content = input.next();
                if ("exit".equals(content)) {
                    log.info("关闭连接......");
                    socket.close();
                    break;
                }
                socket.emit("message-model", content);
            }
        }
    }

}
