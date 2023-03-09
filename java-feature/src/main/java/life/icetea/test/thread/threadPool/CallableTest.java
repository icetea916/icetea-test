package life.icetea.test.thread.threadPool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * callable 线程创建和执行方式
 *
 * @author icetea
 * @date 2023-03-09 13:26
 */
public class CallableTest {

    /**
     * 使用FutureTask进行创建
     */
    @Test
    public void method1() throws ExecutionException, InterruptedException {
        // 需要借助FutureTask 来创建callable线程
        // FutureTask实现了Runnable和Future接口:这样FutureTask就相当于是消费者和生产者的桥梁了，消费者可以通过FutureTask存储任务的执行结果，跟新任务的状态：未开始、处理中、已完成、已取消等等。
        FutureTask<String> futureTask = new FutureTask(() -> {
            System.out.println("执行callable任务");
            Thread.sleep(3000);
            return "hello callable!";
        });

        // 运行
        new Thread(futureTask).start();

        System.out.println("是否执行完成, isDone=" + futureTask.isDone());

        // 获取结果
        String result = futureTask.get();
        System.out.println("获取执行结果，result=" + result);
    }

}
