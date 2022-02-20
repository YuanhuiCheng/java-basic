package com.yuanhui.tutorial.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * add/remove 抛出异常
 * offer/poll 不抛出异常
 * element 检测队首元素，抛出异常
 * peek 检测队首元素，不抛出异常
 * put/take 一直阻塞
 * offer/poll 设置 timeout，超时等待
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        test3();
    }

    /**
     * 抛出异常
     */
    public static void test1() {
        ArrayBlockingQueue q = new ArrayBlockingQueue<>(3);
        System.out.println(q.add(1));
        System.out.println(q.add(2));
        System.out.println(q.add(3));
        // 抛出异常
        // System.out.println(q.add(4));

        System.out.println(q.remove());
        // 队首
        System.out.println(q.element());

        System.out.println(q.remove());
        System.out.println(q.remove());
        // 抛出异常
        // System.out.println(q.element());
        // 抛出异常
        // System.out.println(q.remove());
    }

    public static void test2() {
        ArrayBlockingQueue<Object> q = new ArrayBlockingQueue<>(3);
        System.out.println(q.offer(1));
        System.out.println(q.offer(2));
        System.out.println(q.offer(3));
        // 不抛出异常
        System.out.println(q.offer(4));

        // 队首
        System.out.println(q.peek());

        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        // 不抛出异常
        System.out.println(q.poll());
    }

    /**
     * 等待，阻塞（一直阻塞）
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue q = new ArrayBlockingQueue<>(3);

        // 一直阻塞
        q.put("a");
        q.put("b");
        q.put("c");

        // 一直等待
        // q.put("d");

        System.out.println(q.take());
        System.out.println(q.take());
        System.out.println(q.take());

        // 一直等待
        // System.out.println(q.take());
    }

    /**
     * 等待，阻塞（等待超时）
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue q = new ArrayBlockingQueue<>(3);

        // 一直阻塞
        q.offer("a");
        q.offer("b");
        q.offer("c");

        // 等待2s没位置超时就自动退出
        q.offer("d", 2, TimeUnit.SECONDS);

        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());

        // 等待2s没位置超时就自动退出
        System.out.println(q.poll(2, TimeUnit.SECONDS));
    }
}
