package com.yuanhui.tutorial.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 不保证原子性
 */
public class JMMDemo2 {
    // private volatile static int num = 0;
    private static final AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) {
        // 理论上 num=20000
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }

    /**
     * 获得这个值
     * +1
     * 写回这个值
     */
    public static void add() {
        // num++;
        num.getAndIncrement(); // cas +1
    }
}
