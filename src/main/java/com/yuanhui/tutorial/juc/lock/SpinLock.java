package com.yuanhui.tutorial.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLock {
    AtomicReference<Thread> reference = new AtomicReference<>();

    // 加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "->mylock ");

        // 自旋锁
        while (!reference.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + " is spinning");
        }
        System.out.println(Thread.currentThread().getName() + " reaches here");
    }

    // 解锁
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "->myunlock");
        reference.compareAndSet(thread, null);
    }
}
