package com.yuanhui.tutorial.juc.lock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.sms();
        }, "a").start();

        new Thread(()->{
            phone.sms();
        }, "b").start();
    }
}

class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + " sends sms");
        call();
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " calls");
    }
}
