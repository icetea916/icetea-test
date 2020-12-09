import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestOpenOffice {

    public static void main(String[] args) throws ConnectException, FileNotFoundException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                10,
                200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for (int i = 0; i < 100; i++) {
            MyTask myTask = new MyTask();
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完毕的任务数目：" + executor.getCompletedTaskCount());
        }

        executor.shutdown();
    }

}
