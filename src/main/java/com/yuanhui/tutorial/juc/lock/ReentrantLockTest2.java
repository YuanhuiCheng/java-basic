package com.yuanhui.tutorial.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        new Thread(()->{
            phone.sms();
        }, "a").start();

        new Thread(()->{
            phone.sms();
        }, "b").start();
    }
}

class Phone2 {
    Lock lock = new ReentrantLock();

    public void sms() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sends sms");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        call();
    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " calls");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
