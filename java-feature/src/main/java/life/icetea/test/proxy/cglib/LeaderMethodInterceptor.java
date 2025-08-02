package life.icetea.test.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LeaderMethodInterceptor implements MethodInterceptor {

    /**
     * 调用代理对象的任一方法都会执行该方法，方法拦截器
     *
     * @param o           由CGLib动态生成的代理类实例
     * @param method      表示要被拦截的方法
     * @param objects     表示要被拦截方法的参数
     * @param methodProxy 表示要触发父类的方法对象
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 代理meeting方法
        if ("meeting".equals(method.getName())) {
            System.out.println("cglib代理:先准备会议材料...");
            return methodProxy.invokeSuper(o, objects);
        } else if ("evaluate".equals(method.getName())) { // 代理evaluate方法
            if (objects[0] instanceof String) {
                if ("icetea".equals(objects[0])) {
                    System.out.println("cglib: 犯过错误，所以考评分数较低...");
                    return 70;
                }
            }
            return methodProxy.invokeSuper(o, objects);
        }
        return methodProxy.invokeSuper(o, objects);
    }
}
