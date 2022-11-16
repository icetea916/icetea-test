package life.icetea.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author icetea
 * @date 2022-11-04 20:23
 */
public class DirectMemoryOOM {


    private static final int _1MB = 1024 * 2024 * 100;

    /**
     * 使用unsafe分配本机内存
     * -Xmx20M -XX:MaxDirectMemorySize=10M
     *
     * @param args
     */
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

}
