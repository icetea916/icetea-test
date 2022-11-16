package life.icetea.test.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://blog.csdn.net/zsx_xiaoxin/article/details/123898171
 *
 * @author icetea
 * @date 2022-10-18 13:36
 */
@Slf4j
public class TestCompletableFuture2 {

    /**
     * 创建带返回值的异步任务
     **/
    @Test
    public void testCreateSupply() throws ExecutionException, InterruptedException {
        // 使用默认线程池
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ", do something......");
            return "result";
        });

        // 如果完成则返回结果，否则抛出异常
        System.out.println("结果->" + cf1.get());

        // 使用自定义线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ", do something......");
            return "result";
        }, executor);

        System.out.println("结果->" + cf2.get());
    }

    /**
     * 创建无返回值的异步任务
     **/
    @Test
    public void testCreateRun() throws ExecutionException, InterruptedException {
        // 使用默认线程池
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ", do something......");
        });

        System.out.println("结果->" + cf1.get());

        // 使用自定义线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ", do something......");
        }, executor);

        System.out.println("结果->" + cf2.get());
    }

    /**
     * 异步回调处理
     **/
    @Test
    public void testThen() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf1 do something....");
            return 1;
        });
        // thenApplyAsync使用相同线程进行运算
        CompletableFuture<Integer> cf2 = cf1.thenApply((result) -> {
            System.out.println(Thread.currentThread() + " cf2 do something....");
            return result + 1;
        });

        System.out.println("cf1结果->" + cf1.get());
        System.out.println("cf2结果->" + cf2.get());

        CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf3 do something....");
            return 3;
        });
        // thenApplyAsync使用不同线程进行运算
        CompletableFuture<Integer> cf4 = cf3.thenApplyAsync((result) -> {
            System.out.println(Thread.currentThread() + " cf4 do something....");
            return result + 4;
        });

        System.out.println("cf3结果->" + cf3.get());
        System.out.println("cf4结果->" + cf4.get());
    }

    /**
     * 多任务组合allOf
     **/
    @Test
    public void testAllOf() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + " cf1 do something....");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf1 任务完成");
            return "cf1 任务完成";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + " cf2 do something....");
                int a = 1 / 1;
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf2 任务完成");
            return "cf2 任务完成";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + " cf2 do something....");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf3 任务完成");
            return "cf3 任务完成";
        });

        CompletableFuture<Void> cfAll = CompletableFuture.allOf(cf1, cf2, cf3);
        try {
            cfAll.get();
            System.out.println("cfAll执行结果->" + cfAll.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多任务组合anyOf
     **/
    @Test
    public void testAnyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + " cf1 do something....");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf1 任务完成");
            return "cf1 任务完成";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + " cf2 do something....");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf2 任务完成");
            return "cf2 任务完成";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + " cf2 do something....");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf3 任务完成");
            return "cf3 任务完成";
        });

        CompletableFuture<Object> cfAll = CompletableFuture.anyOf(cf1, cf2, cf3);
        System.out.println("cfAll结果->" + cfAll.get());
    }

}
