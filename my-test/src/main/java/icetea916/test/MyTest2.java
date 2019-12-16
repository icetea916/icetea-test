package icetea916.test;

import org.junit.Test;

public class MyTest2 {

    @Test
    public void testInteger() {
        Integer i1 = new Integer(100);
        Integer i2 = 100;

        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));

        Integer i3 = new Integer(50001);
        System.out.println(i3 == 50001);
    }

}
