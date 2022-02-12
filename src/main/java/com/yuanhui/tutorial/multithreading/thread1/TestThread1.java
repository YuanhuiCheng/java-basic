package com.yuanhui.tutorial.multithreading.thread1;

public class TestThread1 extends Thread {
    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();
        // 子线程执行 run()
        testThread1.start();
        // 主线程执行 run()
        // testThread1.run();

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
