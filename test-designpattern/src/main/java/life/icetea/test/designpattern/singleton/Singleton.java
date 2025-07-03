package life.icetea.test.designpattern.singleton;

/**
 * 饿汉模式
 */
public class Singleton {

    private static final Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }

}
