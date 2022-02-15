import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义实现布隆过滤器
 *
 * @author icetea
 */
public class MyBloomFilter {

    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * 通过这个数组可以创建6个不同的哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 位数组,数组中的元素只能是0或者1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 存放宝行hash 函数的类的数组
     */
    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个包含 hash 函数的类和数组， 每个类中的hash 函数都不一样
     */
    private MyBloomFilter() {
        //初始化多个不同的 hash 函数
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组中
     */
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 静态内部类 用于hash操作
     */
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & (h = value.hashCode())) ^ (h >>> 16);
        }
    }

    public static void main(String[] args) {
        String[] str = {"good", "good", "good"};

        if (str == null || str.length == 0) {
//            return "Fail!";
            System.out.println("Fail!");
        }

        Map<String, List<String>> collect = Arrays.stream(str).collect(Collectors.groupingBy(String::toString));

        List<String> goodIdeas = collect.get("good");

        if (goodIdeas == null) {
//            return "Fail!";
            System.out.println("Fail!");
        } else if (goodIdeas.size() > 2) {
//            return "I smell a series!";
            System.out.println("I smell a series!");
        } else {
            //        return "Publish!";
            System.out.println("Publish!");
        }
    }

}
