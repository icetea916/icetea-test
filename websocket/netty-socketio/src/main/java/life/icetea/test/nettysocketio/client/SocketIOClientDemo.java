package life.icetea.test.nettysocketio.client;

import com.alibaba.fastjson.JSONObject;
import io.socket.client.IO;
import io.socket.client.Socket;
import life.icetea.test.nettysocketio.domain.PushMessage;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * socket io client demo
 * <p>
 * 使用1.0.1版本socketio client
 * 文档：https://socketio.github.io/socket.io-client-java/installation.html
 * github： https://github.com/socketio/socket.io-client-java
 *
 * @author icetea
 */
@Slf4j
public class SocketIOClientDemo {

    public static String token = "icetea";
    public static String url = "http://127.0.0.1:9099?token=" + token;

    public static void main(String args[]) throws URISyntaxException, InterruptedException {
        // client连接参数设置
        IO.Options options = new IO.Options();
//        options.port = 9099;
        // host、ip后的路径
        options.path = "/socket.io";
//        options.transports = new String[]{"websocket"};
        options.reconnection = true; // 进行重连,默认true
        // 重连间隔时间 单位ms
        options.reconnectionDelay = 1000;
        // 最大重连间时间,即一直连不上的最大时间 单位ms
        options.reconnectionDelayMax = 2000;
        // 连接重试次数
        options.reconnectionAttempts = 2;
        // 连接超时时间 ms
        options.timeout = 1000;
        // client创建
        Socket socket = IO.socket(url, options);

        // 事件绑定
        socket.on(Socket.EVENT_CONNECTING, (data) -> log.info("正在连接 connecting"))
                .on(Socket.EVENT_RECONNECTING, (data) -> log.error("重新连接 reconnect"))
                .on(Socket.EVENT_RECONNECT_ERROR, (data) -> log.error("重新连接出错 reconnect error"))
                .on(Socket.EVENT_RECONNECT_FAILED, (data) -> log.error("重新连接失败 reconnect fail"))
                .on(Socket.EVENT_CONNECT_TIMEOUT, (data) -> log.info("连接超时 connect timeout"))
//                .on(Socket.EVENT_PING, (data) -> log.info("心跳 ping"))
//                .on(Socket.EVENT_PONG, (data) -> log.info("心跳 pong"))
                .on(Socket.EVENT_CONNECT, (data) -> log.info("连接成功 connect"))
                .on(Socket.EVENT_DISCONNECT, (data) -> {
                    log.info("断开连接 disconnect");
//                    System.exit(0);
                })
                .on(Socket.EVENT_CONNECT_ERROR, (exception) -> {
                    // 异常 = EngineIOException
                    Exception e = (Exception) exception[0];
                    log.error("连接出错 connect error", e);
                });

        // 绑定接收消息事件（自定义）
        socket.on("message", (data) -> log.info("接收消息： message={}", data.toString()));

        // 连接
        socket.open();


        //创建Executor- Service，通 过它你可以 向线程池提 交任务
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> {
            // 以异步方式在新的线程中执行耗时的操作
            // 发送消息
            Scanner input = new Scanner(System.in);
            while (true) {
                System.out.println("请输入消息(回车发送):");
                String content = input.nextLine();
                if ("exit".equals(content)) {
                    log.info("关闭连接......");
                    socket.close();
                }
                PushMessage msg = new PushMessage(null, null, content);
                socket.emit("message", JSONObject.toJSON(msg));
            }
        });
    }

}
