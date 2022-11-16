package life.icetea.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icetea
 * @date 2022-11-04 20:23
 */
public class RuntimeConstantPoolOOM {

    /**
     * -XX:PermSize=10M -XX:MaxPermSize=10M
     * jdk7及以上 要设置对的大小，因为字符串常量池移至java堆中，-Xmx6m
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

}
