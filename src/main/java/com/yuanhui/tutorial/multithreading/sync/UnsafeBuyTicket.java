package com.yuanhui.tutorial.multithreading.sync;

/**
 * 线程不安全
 */
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "dawn").start();
        new Thread(station, "jack").start();
        new Thread(station, "jennie").start();
    }
}

class BuyTicket implements Runnable {
    boolean flag = true;
    private int ticketCnt = 10;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buy() throws InterruptedException {
        if (ticketCnt <= 0) {
            flag = false;
            return;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " get ticket " + ticketCnt--);
    }
}
