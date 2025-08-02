package life.icetea.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CreateThreadTest {

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();
        System.out.println("test 1");

        Thread threadTest2 = new Thread(new RunnableTest());
        threadTest2.start();
        System.out.println("test 2");

        FutureTask<String> stringFutureTask = new FutureTask<>(new CallableTest());
        Thread thread3 = new Thread(stringFutureTask);
        thread3.start();
        try {
            String s = stringFutureTask.get();
            System.out.println("test 3 result=" + s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    static class CallableTest implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("test create thread method3 ");
            return "success";
        }
    }

    static class RunnableTest implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("test create thread method2 ");
        }
    }


    static class ThreadTest extends Thread {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("test create thread method1 ");
        }

    }

}
