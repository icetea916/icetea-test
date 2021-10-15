package life.icetea.test.designpattern.singleton;

/**
 * 简单懒加载模式
 */
public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {

    }

    private static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }

}
