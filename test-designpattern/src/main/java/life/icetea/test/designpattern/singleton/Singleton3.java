package life.icetea.test.designpattern.singleton;

/**
 * 静态内部类懒加载模式
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {
    }

    public static class LazyHolder {
        private static final Singleton3 singleton3 = new Singleton3();
    }


    private static Singleton3 getInstance() {
        return LazyHolder.singleton3;
    }

}
