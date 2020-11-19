package icetea.test.java.proxy.staticstate;

import icetea.test.java.proxy.Leader;

/**
 * 静态代理测试
 */
public class Test1 {

    public static void main(String[] args) {
        Secretary secretary = new Secretary(new Leader());
        secretary.meeting();
        secretary.evaluate("icetea");
    }

}
