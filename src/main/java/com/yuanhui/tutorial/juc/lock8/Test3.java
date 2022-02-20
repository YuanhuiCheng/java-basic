package com.yuanhui.tutorial.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁，即关于锁的八个问题
 * 5. 增加两个静态的同步方法。只有一个对象。
 * <p>
 * 发短信优先
 * 6. 两个对象，依然发短信优先
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();

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

// phone3 只有一个唯一的 class 对象
class Phone3 {
    // static 类一加载就有了 锁的是 class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send sms");
    }

    public static synchronized void call() {
        System.out.println("call");
    }
}
