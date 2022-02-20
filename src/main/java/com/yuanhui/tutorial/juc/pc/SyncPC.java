package com.yuanhui.tutorial.juc.pc;

/**
 * 线程之间通信问题，生产者和消费者问题。等待唤醒，通知唤醒。
 * 线程交替执行，A，B 操作同一个变量
 * A num+1
 * B num-1
 */
public class SyncPC {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class Data {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        // 虚假唤醒
        /**
         * 用if判断的话，唤醒后线程会从 wait 之后的代码开始运行，但是不会重新判断 if 条件，直接继续运行 if 代码块之后的代码。
         * 而如果使用 while 的话，也会从 wait 之后的代码运行，但是唤醒后会重新判断循环条件，如果不成立再执行 while 代码块之后的代码块，
         * 成立的话继续 wait 。
         */
//        if (number != 0) {
//            // wait
//            this.wait();
//        }
        while (number != 0) {
            // wait
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // notify other threads
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
//        if (number == 0) {
//            // wait
//            this.wait();
//        }
        while (number == 0) {
            // wait
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // notify other threads
        this.notifyAll();
    }
}
