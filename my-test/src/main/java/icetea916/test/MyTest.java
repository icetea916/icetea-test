package icetea916.test;


import org.junit.Test;

import java.util.ArrayList;

public class MyTest {

    @Test
    public void test1() {
        ArrayList<Object> list = new ArrayList<>();
        for (Object o : list) {
            System.out.println(o);
        }

        ArrayList<Object> list1 = null;
        for (Object o : list1) {
            System.out.println(o);
        }
    }


}
