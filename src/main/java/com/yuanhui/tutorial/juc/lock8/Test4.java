package com.yuanhui.tutorial.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁，即关于锁的八个问题
 * 7. 1个静态同步方法，1个同步方法，一个对象
 * 打电话优先
 * 8. 两个对象
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();

        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

class Phone4 {
    // 静态同步方法，锁的是 class 模板
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send sms");
    }

    // 普通同步方法，锁的调用者
    public synchronized void call() {
        System.out.println("call");
    }
}
