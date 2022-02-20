package com.yuanhui.tutorial.juc.pool;

import java.util.concurrent.*;

/**
 * abortPolicy: 抛异常
 * callerRunsPolicy: 哪儿来的去哪里，所以 main 线程处理
 * discardPolicy: 不抛异常
 * discardOldestPolicy: 队列满了，把第一个任务丢弃，执行当前的
 */

/**
 * 最大线程如何定义？
 * cpu 密集型：几核就定义为几，16条线程同时执行，可以保证线程执行效率最高。
 * io 密集型：判断程序中十分占 io 的线程数。15个大型任务，io 十分占用资源。一般把线程数设置为两倍（30）。
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            // 最大承载线程数： deque size + max
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
