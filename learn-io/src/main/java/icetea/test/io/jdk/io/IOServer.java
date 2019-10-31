package icetea.test.io.jdk.io;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * jdk方式创建serverSocket
 */
public class IOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);

        // 接受并连接新线程
        new Thread(() -> {
            while (true) {
                try {
                    // 阻塞方法获取新链接
                    Socket socket = serverSocket.accept();
                    // 每一个新的链接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while (true) {
                                int len;
                                // 按字节流读取数据
                                while ((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
