package com.yuanhui.tutorial.juc.ticketsale;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 真正的多线程开发，公司中的开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属操作
 * 1. 属性，方法
 */

/**
 * synchronized vs lock
 * 1. synchronized java 内置关键字，lock 是一个 java 类。
 * 2. synchronized 无法判断获取锁的状态，lock 可以判断是否获取到了锁。
 * 3. synchronized 会自动释放锁，lock 必须手动释放锁。如果不释放锁，会产生死锁。
 * 4. synchronized 线程 1 （获得锁），线程 2 （等待）；lock 锁不一定会等待。
 * 5. synchronized 可重入锁，非公平；lock 可重入锁，可以判断锁，非公平，可以自己设置。
 * 6. synchronized 适合较少量的代码同步问题，lock 适合锁大量的同步代码。
 */
public class TicketSale2 {
    public static void main(String[] args) {
        // 并发，多线程操作同一个资源类，把资源丢入线程
        Ticket2 ticket = new Ticket2();

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
class Ticket2 {
    Lock lock = new ReentrantLock();
    private int cnt = 50;

    public void sale() {
        lock.lock();

        // lock.tryLock();

        try {
            if (cnt > 0) {
                System.out.println(Thread.currentThread().getName() + " sold " + cnt-- + " tickets");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
