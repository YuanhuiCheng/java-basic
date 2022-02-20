package com.yuanhui.tutorial.juc.aid;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // initial: 6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " left");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await(); // 等待计数器归零，再向下执行。
        System.out.println("close door");
    }
}
