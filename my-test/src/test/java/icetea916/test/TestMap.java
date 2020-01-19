package icetea916.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class TestMap {

    /**
     * 测试keySet的视图特性
     */
    @Test
    public void testMapAndSet() {
        Map<String, String> map = new HashMap<>();
        map.put("icetea1", "icetea11");
        map.put("icetea2", "icetea22");
        map.put("icetea3", "icetea33");

        HashSet<String> set = new HashSet<>();
        set.add("icetea2");

        Set<String> keySet = map.keySet();
        keySet.removeAll(set);
        log.info("keySet={}", keySet);

        log.info("map={}", map);
    }
}
