package life.icetea.test.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;

/**
 * channel
 *
 * @author icetea
 */
public class ChannelDemo01 {

    public static void main(String[] args) throws IOException {
//        transferFrom();
        transferTo();
    }

    /**
     * 从一个channel中读取数据到现在的channel中
     */
    public static void transferFrom() throws IOException {
        URL fromResource = ChannelDemo01.class.getResource("/nio-data.txt");
        RandomAccessFile fromFile = new RandomAccessFile(fromResource.getFile(), "r");
        FileChannel fromFileChannel = fromFile.getChannel();

        URL toResource = ChannelDemo01.class.getResource("/");
        RandomAccessFile toFile = new RandomAccessFile(new File(toResource.getFile(), "/toFile.txt"), "rw");

        long position = 0l;
        long size = fromFileChannel.size();
        toFile.getChannel().transferFrom(fromFile.getChannel(), position, size);
    }

    /**
     * 将现在channel的数据写到指定的channel中
     */
    public static void transferTo() throws IOException {
        URL fromResource = ChannelDemo01.class.getResource("/nio-data.txt");
        RandomAccessFile fromFile = new RandomAccessFile(fromResource.getFile(), "r");
        FileChannel fromFileChannel = fromFile.getChannel();

        URL toResource = ChannelDemo01.class.getResource("/");
        RandomAccessFile toFile = new RandomAccessFile(new File(toResource.getFile(), "/toFile.txt"), "rw");

        long position = 0l;
        long size = fromFileChannel.size();
        fromFileChannel.transferTo(position, size, toFile.getChannel());
    }

}
