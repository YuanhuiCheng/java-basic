package com.yuanhui.tutorial.juc.lock;

import java.util.concurrent.TimeUnit;

public class SpinLockTest {
    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "t2").start();
    }
}
