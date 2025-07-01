import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TestGuavaBloomFilter {

    @Test
    public void testGuavaBloomFilter() {
        // 创建布隆过滤器，预计查询100条数据，误报率0.01
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 100, 0.01);
        List<String> list = Stream.of("good", "hello", "world", "icetea").collect(Collectors.toList());
        // 添加数据
        list.stream().forEach(bloomFilter::put);

        Assert.assertTrue(bloomFilter.mightContain("hello"));
        Assert.assertTrue(bloomFilter.mightContain("icetea"));
        Assert.assertFalse(bloomFilter.mightContain("Publish"));

    }

}
