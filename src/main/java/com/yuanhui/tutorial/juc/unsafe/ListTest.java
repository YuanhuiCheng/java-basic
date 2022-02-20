package com.yuanhui.tutorial.juc.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// java.util.ConcurrentModificationException 并发修改异常
public class ListTest {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1", "2", "3");
//        list.forEach(System.out::println);

        // 并发下 ArrayList 不安全
        /**
         * 解决方法：
         */
        // List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        // CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略
        // 多个线程调用的时候，List，读取的时候，固定的。
        // 写入时，复制一个数组出来，避免覆盖，造成数据问题。
        /**
         * CopyOnWriteList vs Vector
         * Vector 使用 synchronized，写入效率低。
         * 其实 jdk11 之后 CopyOnWriteList 用的也是 synchronized 修饰代码块
         */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
