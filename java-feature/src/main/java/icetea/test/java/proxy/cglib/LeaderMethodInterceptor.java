package icetea.test.java.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LeaderMethodInterceptor implements MethodInterceptor {

    /**
     * @param o           由CGLib动态生成的代理类实例
     * @param method      表示要被拦截的方法
     * @param objects     表示要被拦截方法的参数
     * @param methodProxy 表示要触发父类的方法对象
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if ("meeting".equals(method.getName())) {
            System.out.println("代理先准备会议材料...");
            return methodProxy.invokeSuper(o, objects);
        } else if ("evaluate".equals(method.getName())) {
            if (objects[0] instanceof String) {
                if ("icetea".equals(objects[0])) {
                    System.out.println("icetea 犯过错误，所以考评分数较低...");
                    return 70;
                }
            }
            return methodProxy.invokeSuper(o, objects);
        }
        return methodProxy.invokeSuper(o, objects);
    }
}
