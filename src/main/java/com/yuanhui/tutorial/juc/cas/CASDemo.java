package com.yuanhui.tutorial.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        // AtomicStampedReference, 注意，如果泛型是一个包装类，注意对象的引用问题
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(1, 1);
        new Thread(() -> {
            int stamp = reference.getStamp(); // 获得版本号
            System.out.println("a1=> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.compareAndSet(1, 2,
                    reference.getStamp(), reference.getStamp() + 1));
            System.out.println("a2=>" + reference.getStamp());

            System.out.println(reference.compareAndSet(2, 1,
                    reference.getStamp(), reference.getStamp() + 1));
            System.out.println("a3=>" + reference.getStamp());
        }, "a").start();

        new Thread(() -> {
            int stamp = reference.getStamp(); // 获得版本号
            System.out.println("b1=> " + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(reference.compareAndSet(1, 6,
                    stamp, stamp + 1));
            System.out.println("b2=>" + reference.getStamp());
        }, "b").start();

        // 如果期望值达到了，就更新，否则不更新
        // === 捣乱的线程 ===
//        atomicInteger.compareAndSet(2020, 2021);
//        System.out.println(atomicInteger.get());
//
//        atomicInteger.compareAndSet(2020, 2022);
//        System.out.println(atomicInteger.get());
//
//        // === 期望的线程 ===
//        atomicInteger.compareAndSet(2020, 2021);
//        System.out.println(atomicInteger.get());
    }
}
