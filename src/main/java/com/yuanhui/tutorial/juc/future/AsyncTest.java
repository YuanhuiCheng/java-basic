package com.yuanhui.tutorial.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步回调:
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class AsyncTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(4);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " runAsync");
//        });
//
//        System.out.println("111");
//        future.get();

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supply async => integer");
            int i = 10/0;
            return 1024;
        });
        future.whenComplete((t, u) -> {
            System.out.println("t=" + t);
            System.out.println("u=" + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233;
        }).get();
    }
}
