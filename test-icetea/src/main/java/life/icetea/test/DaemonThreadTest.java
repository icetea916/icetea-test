package life.icetea.test;


public class DaemonThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread(DaemonThreadTest::print);

        thread.setDaemon(false);
        thread.start();

        System.out.println("退出Main方法");

    }

    public static void print() {
        int counter = 1;
        while (true) {
            try {
                System.out.println("Counter:" + counter++);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
