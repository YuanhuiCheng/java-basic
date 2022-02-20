package com.yuanhui.tutorial.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        // 适配类
        FutureTask<Integer> futureTask = new FutureTask<>(thread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start(); // FutureTask 只会被线程 run 一次

        Integer res = futureTask.get(); // get 方法可能会产生阻塞，把它放到最后
        System.out.println(res);
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return 1024;
    }
}