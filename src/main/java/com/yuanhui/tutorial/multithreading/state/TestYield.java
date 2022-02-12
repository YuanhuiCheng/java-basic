package com.yuanhui.tutorial.multithreading.state;

/**
 * 测试礼让线程
 * 礼让不一定成功，看 CPU 心情
 */
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();

        new Thread(myYield, "a").start();
        new Thread(myYield, "b").start();
    }
}

class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println("start thread " + Thread.currentThread().getName());
        Thread.yield();
        System.out.println("stop thread " + Thread.currentThread().getName());
    }
}
