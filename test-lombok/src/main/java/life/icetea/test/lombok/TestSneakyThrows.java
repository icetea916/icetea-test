package life.icetea.test.lombok;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

/**
 * SneakyThrows注解示例
 * <p>
 * https://www.jianshu.com/p/7d0ed3aef34b
 * <p>
 * SneakyThrows注解用来消除样板代码：
 * try{
 * ······
 * }catch(Exception e){
 * throw new RuntimeException(e);
 * }
 * <p>
 * 查看编译后的class文件会看到效果
 *
 * @author icetea
 */
public class TestSneakyThrows implements Runnable {

    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }


    @Override
    @SneakyThrows
    public void run() {
        throw new Throwable();
    }

}
