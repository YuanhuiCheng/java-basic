package com.yuanhui.tutorial.multithreading.lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();

        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }
}

class TestLock2 implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();
    int tickCnt = 10;

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock(); // 加锁
                if (tickCnt > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tickCnt--);
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
