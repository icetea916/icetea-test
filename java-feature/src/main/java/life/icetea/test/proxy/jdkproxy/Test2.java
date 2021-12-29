package life.icetea.test.proxy.jdkproxy;

import life.icetea.test.proxy.IWork;
import life.icetea.test.proxy.Leader;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理测试
 */
public class Test2 {

    public static void main(String[] args) throws FileNotFoundException {
        Leader leader = new Leader();
        IWork proxy = (IWork) Proxy.newProxyInstance(Leader.class.getClassLoader()
                , new Class[]{IWork.class}
                , new WorkInvocationHandler(leader));
        proxy.meeting();
        proxy.evaluate("icetea");

        //导出内存中的class
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", Leader.class.getInterfaces());
        String path = "$Proxy0.class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(bytes);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
            e.printStackTrace();
        }
    }

}
