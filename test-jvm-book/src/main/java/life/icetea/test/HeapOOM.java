package life.icetea.test;

import java.util.ArrayList;

/**
 * @author icetea
 * @date 2022-11-03 21:07
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        while (true) {
            objects.add(new OOMObject());
        }
    }
    
}
