package icetea.test.java.proxy.jdk;

import icetea.test.java.proxy.IWork;
import icetea.test.java.proxy.Leader;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理测试
 */
public class Test2 {

    public static void main(String[] args) {
        Leader leader = new Leader();
        IWork proxy = (IWork) Proxy.newProxyInstance(Leader.class.getClassLoader()
                , new Class[]{IWork.class}
                , new WorkInvocationHandler(leader));
        proxy.meeting();
        proxy.evaluate("icetea");
    }

}
