package life.icetea.test.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author icetea
 * https://blog.csdn.net/sermonlizhi/article/details/123356877
 * @date 2022-10-18 13:36
 */
@Slf4j
public class TestFuture {

    @Test
    public void test1() {
        Runnable runnable = () -> System.out.println("无返回结果异步任务");
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(runnable);

        Supplier<String> supplier = () -> {
            System.out.println("有返回结果异步任务");
            return "icetea";
        };

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(supplier);
        // 获取结果方式1 get()方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch）
        String s = null;
        try {
            s = stringCompletableFuture.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取结果方式2, join()方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出
        String join = stringCompletableFuture.join();
        System.out.println(join);
    }

    // 结果处理
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (new Random().nextInt(10) % 2 == 0) {
                int i = 12 / 0;
            }
            System.out.println("执行结束");
            return "icetea";
        });

        // 任务完成或出现异常执行方法，若为异常则记过为null
        completableFuture.whenComplete((v, e) -> {
            System.out.println(v);
        });

        // 出现异常是先执行改方法
        completableFuture.exceptionally(t -> {
            System.out.println("执行失败，" + t.getMessage());
            return "icetea异常";
        });

        completableFuture.get();
    }

    // 结果转换
    @Test
    public void test3() {
        // 使用apply
        CompletableFuture<Integer> oneFuture = CompletableFuture.supplyAsync(() -> {
            int i = 100;
            System.out.println("apply第一次运算:" + i);
            return i;
        }).thenApplyAsync((v) -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int j = v * 3;
            System.out.println("apply第二次运算:" + j);
            return j;
        });

        // 使用compose
        CompletableFuture<Integer> composeOneFuture = CompletableFuture.supplyAsync(() -> {
            int i = new Random().nextInt(30);
            System.out.println("compose第一次运算结果:" + i);
            return i;
        }).thenComposeAsync((v) -> {
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
                int i = v * 10;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("compose第二次运算结果:" + i);
                return i;
            });
            return integerCompletableFuture;
        });
    }

    /**
     * Suppliers async list.
     *
     * @param <T>       the type parameter
     * @param suppliers the suppliers
     * @param executor  the executor
     * @return the list
     */
    public static <T> List<T> suppliersAsync(Collection<Supplier<T>> suppliers, Executor executor) {
        // supplier封装成CompletableFuture<T>
        List<CompletableFuture<T>> futureList = suppliers.stream()
                .map(supplier -> CompletableFuture.supplyAsync(supplier, executor)
                        .handleAsync((res, ex) -> {
                            if (null != ex) {
                                log.error("", ex);
                            }
                            return res;
                        })
                ).collect(Collectors.toList());

        /* 组合CompletableFuture */
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{}));

        /* 组合执行结果 */
        CompletableFuture<List<T>> resultCompletableFuture = allOfFuture
                .thenApplyAsync(v -> futureList.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()),
                        executor);

        /* 阻塞获取结果 */
        try {
            return resultCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("", e);
        }
        return Collections.emptyList();
    }

}
