package lambda;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream的使用
 */
public class StreamDemo1 {

    /**
     * flatMap方法：可以将多各stream连城一个stream, 与map方法相同只是限定了返回结果为Stream类型
     */
    @Test
    public void test1() {
        List<Integer> collect = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6))
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5), collect);
    }

    /**
     * max和min
     */
    @Test
    public void test2() {
    }

}
