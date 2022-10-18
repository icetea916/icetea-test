package life.icetea.test.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author icetea
 * @date 2022-10-14 15:39
 */
public class TestString {

    // 连接器
    private static final Joiner joiner = Joiner.on(",").skipNulls();
    // 分割器
    private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();

    /**
     * 对于Joiner，常用的方法是  跳过NULL元素：skipNulls()  /  对于NULL元素使用其他替代：useForNull(String)
     * 对于Splitter，常用的方法是：trimResults()/omitEmptyStrings()。注意拆分的方式，有字符串，还有正则，还有固定长度分割（太贴心了！）
     */
    @Test
    public void testDemo1() {
        String join = joiner.join(Lists.newArrayList("a", null, "b"));
        System.out.println(join);

        for (String s : splitter.split("a,     , b , ,,")) {
            System.out.println(s);
        }
    }

}
