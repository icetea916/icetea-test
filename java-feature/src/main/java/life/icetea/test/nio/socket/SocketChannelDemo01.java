package life.icetea.test.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketChannel是一个连接到TCP网络套接字的通道。可以通过以下2种方式创建SocketChannel：
 * 1. 打开一个SocketChannel并连接到互联网上的某台服务器。
 * 2.一个新连接到达ServerSocketChannel时，会创建一个SocketChannel。
 */
public class SocketChannelDemo01 {

    public static void main(String[] args) throws IOException {
        openMethod1();
//        openMethod2();
    }

    /**
     * socketChannel打开方式一: 直接打开并连接到网上的某一台服务器
     */
    public static void openMethod1() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.configureBlocking(true); // 设置为非阻塞模式
        socketChannel.connect(new InetSocketAddress("localhost", 9999));
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (socketChannel.read(buf) != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
        }
    }

}
