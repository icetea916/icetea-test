package life.icetea.test;

import java.util.concurrent.*;

public class IceteaTest {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        ExecutorService executorService4 = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(IceteaTest::run);
            executorService.execute(IceteaTest::run);
            executorService.submit(IceteaTest::run);
        }

        executorService4.

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 30, TimeUnit.MINUTES, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static void run() {
        System.out.println("hello world");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
