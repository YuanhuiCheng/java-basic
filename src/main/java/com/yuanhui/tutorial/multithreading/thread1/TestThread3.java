package com.yuanhui.tutorial.multithreading.thread1;

/**
 * 不建议直接继承 Thread 类，避免 OOP 单继承局限性。
 * 建议实现 Runnable 接口，灵活方便，方便同一个对象被多个线程使用。
 */
public class TestThread3 implements Runnable {
    public static void main(String[] args) {
        new Thread(new TestThread3()).start();

        for (int i = 0; i < 20; i++) {
            System.out.println("i'm learning multi-thread -- " + i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("i'm reading code -- " + i);
        }
    }
}
