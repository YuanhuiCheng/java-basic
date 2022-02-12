package com.yuanhui.tutorial.multithreading.state;

import java.util.TreeMap;

/**
 * 测试 join 方法，想象成插队
 */
public class TestJoin implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                thread.join(); // 插队
            }
            System.out.println("main " + i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("thread vip " + i);
        }
    }
}
