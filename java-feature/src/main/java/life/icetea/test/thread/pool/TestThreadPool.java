package life.icetea.test.thread.pool;

import java.util.concurrent.*;

/**
 * https://blog.csdn.net/qq_40428665/article/details/121651421
 *
 * @author icetea
 */
public class TestThreadPool {


    public static void main(String[] args) {
        testThreadPoolExecutor();
    }

    /**
     * ThreadPoolExecutor简单创建
     */
    public static void testThreadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, // 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
                10,// 线程数的上限
                200, TimeUnit.MILLISECONDS, // 超过corePoolSize的线程的idle时长，超过这个时间，多余的线程会被回收。
                new ArrayBlockingQueue<Runnable>(5) // 任务的排队队列
        );

        for (int i = 0; i < 20; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目=" + executor.getPoolSize()
                    + " 队列中等待执行的任务数目=" + executor.getQueue().size()
                    + " 已执行完的任务数目=" + executor.getCompletedTaskCount());
        }

        executor.shutdown();
    }

    /**
     * Executors创建的几种线程池
     */
    public static void testExecutors() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    }


}
