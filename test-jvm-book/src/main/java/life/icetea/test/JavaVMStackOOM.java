package life.icetea.test;

/**
 * @author icetea
 * @date 2022-11-03 21:32
 */
public class JavaVMStackOOM {

    private static void dontStop() {
        while (true) {

        }
    }

    public static void main(String[] args) {
        Runnable runnable = () -> dontStop();
        while (true) {
            new Thread(runnable).start();
        }
    }

}
