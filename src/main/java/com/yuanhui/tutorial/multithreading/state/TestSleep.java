package com.yuanhui.tutorial.multithreading.state;

/**
 * 模拟网络延时：放大问题的发生性
 */
public class TestSleep implements Runnable {
    private int ticketCnt = 10;

    public static void main(String[] args) {
        TestSleep testSleep = new TestSleep();

        new Thread(testSleep, "dawn").start();
        new Thread(testSleep, "jack").start();
        new Thread(testSleep, "abby").start();
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
