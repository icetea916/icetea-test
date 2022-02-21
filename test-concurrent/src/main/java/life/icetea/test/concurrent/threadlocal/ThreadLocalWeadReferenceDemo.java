package life.icetea.test.concurrent.threadlocal;

import java.lang.reflect.Field;

/**
 * ThreadLocal 的  WeakReference demo
 *
 * @author icetea
 */
public class ThreadLocalWeadReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> test("adb", false));
        t.start();
        t.join();
        System.out.println("--gc后--");
        Thread t2 = new Thread(() -> test("def", true));
        t2.start();
        t2.join();
    }

    private static void test(String s, boolean isGC) {
        try {
            ThreadLocal<Object> threadLocal = new ThreadLocal<>();
            threadLocal.set(s);
            if (isGC) {
                System.gc();
            }
            Thread t = Thread.currentThread();
            Class<? extends Thread> clz = t.getClass();
            Field field = clz.getDeclaredField("threadLocals");
            field.setAccessible(true);
            Object threadLocalMap = field.get(t);
            Class<?> tlmClass = threadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    Field referentField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referentField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s, 值:%s", referentField.get(o), valueField.get(o)));
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
