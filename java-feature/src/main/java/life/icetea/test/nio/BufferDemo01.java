package life.icetea.test.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 使用Buffer读写数据一般遵循以下四个步骤：
 * <p>
 * 1. 写入数据到Buffer
 * 2. 调用flip()方法
 * 3. 从Buffer中读取数据
 * 4. 调用clear()方法或者compact()方法
 * <p>
 * 当向buffer写入数据时，buffer会记录下写了多少数据。
 * 一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。
 * 在读模式下，可以读取之前写入到buffer的所有数据。
 * 一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。
 * 有两种方式能清空缓冲区：
 * a. 调用clear()或compact()方法。clear()方法会清空整个缓冲区。
 * b. compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
 */
public class BufferDemo01 {

    public static void main(String[] args) throws IOException {
//        URL resource = BufferDemo01.class.getClassLoader().getResource("nio-data.txt");
        URL resource = BufferDemo01.class.getResource("/nio-data.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(resource.getFile(), "rw");
        FileChannel channel = randomAccessFile.getChannel();

//        simpleRead(channel);
        readUTF8(channel);
        randomAccessFile.close();
    }

    private static void simpleRead(FileChannel channel) throws IOException {
        // 创建一个48byte容量的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);

        int bytesRead = channel.read(byteBuffer); //read into buffer.
        while (bytesRead != -1) {
            byteBuffer.flip(); //make buffer ready for read

            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }

            byteBuffer.clear(); // make buffer ready for writing
            channel.read(byteBuffer);
        }
    }

    /**
     * 读取utf-8文件, 因为utf8是可变长度的字符集所以和ascii不一样
     * 下面就是 UTF-8 的编码方式
     * <p>
     * 0xxxxxxx
     * 110xxxxx 10xxxxxx
     * 1110xxxx 10xxxxxx 10xxxxxx
     * 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
     * <p>
     * 对于 UTF-8 编码中的任意字节 B ，如果 B 的第一位为0，则 B 为 ASCII 码，并且 B 独立的表示一个字符；
     * 如果 B 的第一位为1，第二位为0，则B为一个非 ASCII 字符（该字符由多个字节表示）中的一个字节，并且不是字符的第一个字节编码；
     * 如果 B 的前两位为1，第三位为0，则B为一个非 ASCII 字符（该字符由多个字节表示）中的第一个字节，并且该字符由两个字节表示；
     * 如果 B 的前三位为1，第四位为0，则B为一个非 ASCII 字符（该字符由多个字节表示）中的第一个字节，并且该字符由三个字节表示；
     * 如果 B 的前四位为1，第五位为0，则B为一个非 ASCII 字符（该字符由多个字节表示）中的第一个字节，并且该字符由四个字节表示；
     */
    public static void readUTF8(FileChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4); // 至少为4，因为UTF-8最大为4字节
        while (channel.read(buffer) != -1) {
            byte b;
            int idx;
            out:
            for (idx = buffer.position() - 1; idx >= 0; idx--) {
                b = buffer.get(idx);

                if ((b & 0xff) >> 7 == 0) {  // 0xxxxxxx
                    break;
                }
                if ((b & 0xff & 0xc0) == 0xc0) {   // 11xxxxxx，110xxxxx、1110xxxx、11110xxx
                    idx -= 1;
                    break;
                }
                if ((b & 0xff & 0x80) == 0x80) {
                    for (int i = 1; i < 4; i++) {
                        b = buffer.get(idx - i);
                        if ((b & 0xff & 0xc0) == 0xc0) {
                            if ((b & 0xff) >> (5 + 1 - i) == 0xf >> (3 - i)) {
                                break out;
                            } else {
                                idx = idx - 1 - i;
                                break out;
                            }
                        }
                    }
                }
            }

            buffer.flip();
            int limit = buffer.limit();
            buffer.limit(idx + 1);  // 阻止读取跨界数据
            System.out.print(Charset.forName("UTF-8").decode(buffer).toString());

            buffer.limit(limit);  // 恢复limit
            buffer.compact();
        }
    }

}
