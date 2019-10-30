package icetea.rabbitmq;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class IceteaTest {

    @Test
    public void test1() {
        System.out.println("12312321");
    }

    @Test
    public void test2() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("username", "<p>亲爱的专技天下网学员您们好！</p><p>&nbsp;&nbsp;&nbsp; 为给广大学员提供更优质的学习体验，专技天下网于2019.6.22正式上线最新版，如有任何良好建议，请您随时向我们提出，企业邮箱：111@zgzjzj.com，您的建议采纳后会第一时间进行优化，感谢您的支持！<br></p>");
        map1.put("age", 1);
        List<Map<String, Object>> list = new ArrayList<>();

        list.add(map1);

        System.out.println(list);
        System.out.println("=====================");
        // 测试foreach
        list.forEach(map -> {
            String content = (String) map.get("username");
            // 剔除html标签
            content = content.replaceAll("</?[^>]+>", "").replaceAll("&nbsp;", "");
            // 截取70个字符
            content = content.substring(0, 70);
            // 更新字符串
            map.put("username", content);
        });

        System.out.println(list);
    }

    /**
     * 测试bigDecimal
     */
    @Test
    public void test3() {
        BigDecimal big = new BigDecimal("54.001");
        BigDecimal small = new BigDecimal("54");

        System.out.println(big.compareTo(small));
        System.out.println(small.compareTo(big));
    }

}
