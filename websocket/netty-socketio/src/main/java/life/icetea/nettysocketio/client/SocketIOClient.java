package life.icetea.nettysocketio.client;

import life.icetea.nettysocketio.domain.MyMessage;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.util.Scanner;

@Slf4j
public class SocketIOClient {

    public static String username = "icetea";
    public static String url = "http://localhost:9099?loginUserName=" + username;

    public static void main(String args[]) throws URISyntaxException {
        // client连接参数设置
        IO.Options options = new IO.Options();
        options.reconnection = true; // 进行重连,默认true
        options.reconnectionDelay = 10000; // 重连间隔时间 单位ms
        options.reconnectionDelayMax = 5000; // 最大重连间时间,即一直连不上的最大时间 单位ms

        // client创建
        Socket socket = IO.socket(url, options);

        // 事件绑定
        socket.on(Socket.EVENT_CONNECTING, (data) -> {
            log.info("正在连接回调 connecting");
        }).on(Socket.EVENT_CONNECT_ERROR, (exception) -> {
            // 异常 = EngineIOException
            Exception e = (Exception) exception[0];
            log.info("连接出错回调 connect error", e);
        }).on(Socket.EVENT_CONNECT_TIMEOUT, (data) -> {
            log.info("第一次连接超时 connect_timeout");
        }).on(Socket.EVENT_CONNECT, (data) -> {
            log.info("连接成功回调 connect");
        }).on(Socket.EVENT_DISCONNECT, (data) -> {
            log.info("断开连接回调 disconnect ");
            log.info("exit system.......");
            System.exit(0);
        }).on("message", (data) -> {
            log.info("自定义发送消息回调 message={}", data.toString());
        });

        // 连接
        socket.connect();
        if (socket.connected()) {
            log.info("socket.io 连接成功");
        }

        // 发送消息
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("请输入消息:");
            String content = input.next();
            if ("exit".equals(content)) {
                // 退出
                break;
            }
            MyMessage msg = new MyMessage();
            msg.setUsername("icetea-java-client");
            msg.setAge(19);
            msg.setContent(content);
            socket.emit("message-model", msg);
        }

        socket.disconnect();
    }
}
