package com.yuanhui.tutorial.juc.ticketsale;

/**
 * 真正的多线程开发，公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属操作
 * 1. 属性，方法
 */
public class TicketSale {
    public static void main(String[] args) {
        // 并发，多线程操作同一个资源类，把资源丢入线程
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

/**
 * 资源类
 */
class Ticket {
    private int cnt = 50;

    /**
     * synchronized 本质 锁 排队
     */
    public synchronized void sale() {
        if (cnt > 0) {
            System.out.println(Thread.currentThread().getName() + " sold " + cnt-- + " tickets");
        }
    }
}
