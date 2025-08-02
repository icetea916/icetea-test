package life.icetea.test.proxy.cglib;

import life.icetea.test.proxy.Leader;
import net.sf.cglib.proxy.Enhancer;

/**
 * cglib动态代理测试
 */
public class Test3 {

    public static void main(String[] args) {
        //保存生成的 class 文件
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "temp/code");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(Leader.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new LeaderMethodInterceptor());
        // 创建代理对象
        Leader proxy = (Leader) enhancer.create();
        proxy.meeting();
        proxy.evaluate("xiaoming");
        proxy.evaluate("icetea");

    }

}
