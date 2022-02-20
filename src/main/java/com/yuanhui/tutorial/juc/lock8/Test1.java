package com.yuanhui.tutorial.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁，即关于锁的八个问题
 * <p>
 * 1. 标准情况下，两个线程先打印发短信还是打电话？
 * 先发短信，再打电话。
 * <p>
 * 2. 发短信延迟 4 s，先发短信还是打电话？
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        /**
         * sendSms 和 call 的调用者都是 phone，用的是同一把锁。谁先拿到谁先执行。
         */

        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {
    // synchronized 锁的对象是方法的调用者
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send sms");
    }

    public synchronized void call() {
        System.out.println("call");
    }
}
