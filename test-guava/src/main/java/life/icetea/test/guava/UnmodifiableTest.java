package life.icetea.test.guava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnmodifiableTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);

        String temp = unmodifiableList.get(1);
        System.out.println("unmodifiableList [0]：" + temp);

        list.add("baby");
        System.out.println("list add a item after list:" + list);
        System.out.println("list add a item after unmodifiableList:" + unmodifiableList);

        unmodifiableList.add("cc");
        System.out.println("unmodifiableList add a item after list:" + unmodifiableList);
    }
}