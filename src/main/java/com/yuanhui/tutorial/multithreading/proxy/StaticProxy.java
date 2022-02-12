package com.yuanhui.tutorial.multithreading.proxy;

interface Marry {
    void happyMarry();
}

public class StaticProxy {
    public static void main(String[] args) {
        WeddingCompany company = new WeddingCompany(new You());
        company.happyMarry();

        // Thread 是代理类，Runnable 是目标类
        new Thread(() -> System.out.println("i love u")).start();
    }
}

/**
 * 目标类
 */
class You implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("happy marry...");
    }
}

/**
 * 代理类
 */
class WeddingCompany implements Marry {
    private final Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry();
        after();
    }

    public void before() {
        System.out.println("clean up");
    }

    public void after() {
        System.out.println("collect money");
    }
}
