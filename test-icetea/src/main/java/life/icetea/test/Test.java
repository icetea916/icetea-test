package life.icetea.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icetea
 * @date 2025/7/16
 */
public class Test {

    private static List<Test> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            list.add(new Test());
        }
    }
}
