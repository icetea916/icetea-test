package life.icetea.test.guava;

import com.google.common.collect.HashBiMap;

/**
 * BiMap 双向Map，可以通过value查找key，也可以通过key查找value
 * 注：
 * 1. biMap中的value也不可以重复
 * 2. 反向操作inverse方法返回的是一个视图关联，修改该视图会影响到原biMap
 *
 * @author icetea
 * @date 2023/12/29
 */
public class BiMapTest {

    public static void main(String[] args) {
        // 存储人员职业
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("冰红茶", "程序员");
        biMap.put("张三", "学生");
        biMap.put("李四", "教师");
        // biMap中的value也不可以重复, 重复会报错, 如果想覆盖则使用put方法
//        biMap.put("王五", "程序员");
        System.out.println(biMap);
        biMap.forcePut("王五", "程序员");

        // 使用key获取value
        String v = biMap.get("张三");
        System.out.println(v);

        // 根据value查询key
        String k = biMap.inverse().get("程序员");
        System.out.println(k);

        // 反向操作inverse方法返回的是一个视图关联，修改该视图会影响到原biMap
        System.out.println(biMap);
        biMap.inverse().put("程序员", "icetea");
        System.out.println(biMap);

    }

}
