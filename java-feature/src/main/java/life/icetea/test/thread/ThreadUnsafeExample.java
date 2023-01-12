package life.icetea.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author icetea
 * @date 2023-01-12 17:03
 */
public class ThreadUnsafeExample {

    private int count = 0;

    public void add() {
        count++;
    }

    public int get() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final int threadSize = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                System.out.println("++1");
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());
    }

}
