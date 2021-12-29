package life.icetea.test.proxy.jdkproxy;

import life.icetea.test.proxy.IWork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WorkInvocationHandler<T extends IWork> implements InvocationHandler {

    private T target;

    public WorkInvocationHandler(T target) {
        this.target = target;
    }

    /**
     * @param proxy  代理对象
     * @param method 正在调用的方法
     * @param args   入参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被代理类simpleName=" + target.getClass().getSimpleName());
        System.out.println("代理类simpleName=" + proxy.getClass().getSimpleName());

        // before
        if ("meeting".equals(method.getName())) {
            System.out.println("代理类先准备开会材料");
        } else if ("evaluate".equals(method.getName())) {
            if (args[0] instanceof String) {
                if ("icetea".equals(args[0])) {
                    System.out.println("icetea 犯过错误，所以考评分数较低...");
                    return 70;
                }
            }
        }

        // run
        Object resultObj = method.invoke(target, args);

        // after

        return resultObj;
    }

}
