package com.yuanhui.tutorial.multithreading.thread1;

/**
 * 多个线程同时操作同一个对象
 *
 * 多个线程操作同一个资源，数据紊乱。
 */
public class TestThread4 implements Runnable {
    private int ticketCnt = 10;

    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();

        new Thread(testThread4, "dawn").start();
        new Thread(testThread4, "jack").start();
        new Thread(testThread4, "abby").start();
    }

    @Override
    public void run() {
        while (true) {
            if (ticketCnt <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " get ticket " + ticketCnt--);
        }
    }
}
