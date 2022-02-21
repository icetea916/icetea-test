package life.icetea.test.concurrent.threadlocal;


/**
 * ThreadLocal中的hash算法 黄金分割数demo
 *
 * @author icetea
 */
public class ThreadLocalHashIncrementDemo {

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        int hashCode = 0;
        System.out.println(Integer.toBinaryString(15));
        for (int i = 0; i < 16; i++) {
            hashCode = i + HASH_INCREMENT;
            int bucket = hashCode & 15;
            System.out.println(i + " 在桶中的位置：" + bucket);
        }
    }
}
