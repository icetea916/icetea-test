import org.junit.Test;

import java.util.Arrays;

/**
 * 数组拷贝方法
 *
 * @author icetea
 */
public class ArrayCopyTest {

    /**
     * System.arraycopy方法
     * 注:
     * 1. ArrayList的指定数组index进行插入{@link java.util.ArrayList#add(int, Object)},使用了该方法来进行元素的插入,实现了将插入位置之后的元素进行错位copy的功能
     * 2. {@link java.util.Arrays#copyOf(Object[], int)}方法底层也使用的改方法进行实现
     */
    @Test
    public void test1() {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;

        /**
         * 参数1 src: 源数组
         * 参数2 srcPos: 源数组的起始位置(即要从源数组的哪个下标开始进行copy)
         * 参数3 dest: 目标数组
         * 参数4 destPos: 目标数组起始位置(即将数据copy到目标数组的哪个下标开始)
         * 参数5 length: 要复制数组元素的长度
         */
        System.arraycopy(a, 2, a, 3, 1);

        a[2] = 99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * Arrays.copyOf方法
     */
    @Test
    public void test2() {
        int[] a = new int[3];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;

        int[] b = Arrays.copyOf(a, 10);

        System.out.println("b.length=" + b.length);
        System.out.println(Arrays.toString(b));
    }

    /**
     * Object的clone方法
     */
    @Test
    public void test3() {
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2 = a1.clone();

        System.out.println("a1==a2 : " + (a1 == a2));
        System.out.println("a2 = " + Arrays.toString(a2));


    }

    @Test
    public void test4() {
        long a1 = 1644565997000l;
        long a2 = 1644563890999l;

        long b = a1 - a2;
        System.out.println(b);
        System.out.println((long)Math.ceil(b / 1000.0));
    }

}
