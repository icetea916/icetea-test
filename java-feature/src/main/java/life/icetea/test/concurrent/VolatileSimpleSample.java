package life.icetea.test.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.bilibili.com/video/BV1UJ411g7Y3?p=2
 *
 * 验证线程之间对共享变量没有办法保证可见性问题,
 *
 * 原因: JMM模型规定了每个线程有自己单独的内存副本,每个线程在操作共享内存的变量是,会复制一份数据到自己的线程内存(工作内存)中
 * 解决方法: 使用volatile关键字,原理是当变量被修改时会马上回写到共享内存中
 */
@Slf4j
public class VolatileSimpleSample {

//    private static boolean initFlag = false;
    private volatile static boolean initFlag = false;

    private static AtomicInteger i = new AtomicInteger(0);

    private static CountDownLatch latch = new CountDownLatch(3);

    public static void refresh() {
        i.addAndGet(1);
        latch.countDown();
        log.info("refresh data ......");
        initFlag = true;
        log.info("refresh data success ......");
    }


    public static void loadData() {
        while (!initFlag) {
        }
        log.info("线程[{}]嗅探到initFlag的状态改变", Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> loadData(), "thread-A");
        Thread threadB = new Thread(() -> refresh(), "thread-B");

        threadA.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadB.start();

    }
}
