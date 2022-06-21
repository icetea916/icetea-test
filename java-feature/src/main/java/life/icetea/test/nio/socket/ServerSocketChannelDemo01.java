package life.icetea.test.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerSocketChannelDemo01 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
//        server.configureBlocking(true);  // 设置为非阻塞模式,在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null。
        server.socket().bind(new InetSocketAddress(9999));

        // 连接成功echo
        byte[] bytes = "hello world!".getBytes(StandardCharsets.US_ASCII);
        while (true) {
            SocketChannel socketChannel = server.accept();
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.clear();
        }

    }

}
