package com.yuanhui.tutorial.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 八锁，即关于锁的八个问题
 * 3. 增加了一个普通方法 ，先打印发短信还是 hello
 * 先打印 hello
 *
 * 4.两个对象，两个同步方法，先发短信还是打电话？
 * 打电话
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        Phone2 phone2 = new Phone2();

        /**
         * sendSms 和 call 的调用者都是 phone，用的是同一把锁。谁先拿到谁先执行。
         */

//        new Thread(() -> {
//            phone.sendSms();
//        }, "A").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        new Thread(() -> {
//            phone.hello();
//        }, "B").start();

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

class Phone2 {
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

    // 没有锁，不用抢
    public void hello() {
        System.out.println("hello");
    }
}
