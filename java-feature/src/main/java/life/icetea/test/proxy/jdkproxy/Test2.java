package life.icetea.test.proxy.jdkproxy;

import life.icetea.test.proxy.IWork;
import life.icetea.test.proxy.Leader;

import java.io.FileNotFoundException;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理测试
 */
public class Test2 {

    public static void main(String[] args) throws FileNotFoundException {
        Leader leader = new Leader();
        IWork leaderProxy = (IWork) Proxy.newProxyInstance(Leader.class.getClassLoader()
                , Leader.class.getInterfaces()
                , new WorkInvocationHandler(leader));

        // 代理类信息
        Class<? extends IWork> leaderProxyClass = leaderProxy.getClass();
        System.out.println("leaderProxy name=" + leaderProxyClass.getName());
        System.out.println("leaderProxy simpleName=" + leaderProxyClass.getSimpleName());
        System.out.println("leaderProxy canonicalName=" + leaderProxyClass.getCanonicalName());
        System.out.println("leaderProxy typeName=" + leaderProxyClass.getTypeName());

        // 被代理类信息
        Class<? extends IWork> leaderClass = leader.getClass();
        System.out.println("leader name=" + leaderClass.getName());
        System.out.println("leader simpleName=" + leaderClass.getSimpleName());
        System.out.println("leader canonicalName=" + leaderClass.getCanonicalName());
        System.out.println("leader typeName=" + leaderClass.getTypeName());

        leaderProxy.meeting();
        leaderProxy.evaluate("icetea");

        //导出内存中的class
//        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", Leader.class.getInterfaces());
//        String path = "$Proxy0.class";
//        try (FileOutputStream fos = new FileOutputStream(path)) {
//            fos.write(bytes);
//            fos.flush();
//            System.out.println("代理类class文件写入成功");
//        } catch (Exception e) {
//            System.out.println("写文件错误");
//            e.printStackTrace();
//        }
    }

}
