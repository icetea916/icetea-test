package icetea916.test;

import icetea916.test.thread.MyCallable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程测试
 *
 * @author icetea
 */
@Slf4j
public class ThreadTest {

    /**
     * 测试有返回值的线程
     */
    @Test
    public void testThreadCallable() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 创建多个又返回值的任务
        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyCallable c = new MyCallable(i + "");
            Future<String> future = threadPool.submit(c);
            futures.add(future);
        }
        threadPool.shutdown();

        // 输出运行结果
        futures.forEach(future -> {
            try {
                log.info("res:{}", future.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }


}
