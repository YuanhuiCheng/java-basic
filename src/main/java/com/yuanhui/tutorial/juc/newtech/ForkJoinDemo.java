package com.yuanhui.tutorial.juc.newtech;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        test2();
        test3();
    }

    // 普通程序员
    public static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0l;
        for (int i = 0; i < 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("1: sum=" + sum + "; execution time: " + (end - start) + " ms");
    }

    // use forkjoin
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinTest(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("2: sum=" + sum + "; execution time: " + (end - start) + " ms");
    }

    public static void test3() {
        long start = System.currentTimeMillis();
        // stream 并行流
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("3: sum=" + sum + "; execution time: " + (end - start) + " ms");
    }
}
