package com.yuanhui.tutorial.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        // MyCache cache = new MyCache();
        MyCacheLock cache = new MyCacheLock();

        // 写入
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }
        // 读取
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCacheLock {
    private final Map<String, Object> map = new HashMap<>();
    // 读写锁，更加细腻的控制
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    // 写入时，只希望同时只有一个线程写
    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writes " + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " successfully wrote");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 所有人都可读
    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reads " + key);
            Object obj = map.get(key);
            System.out.println(Thread.currentThread().getName() + " successfully read");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

class MyCache {
    private final Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + " writes " + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " successfully wrote");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " reads " + key);
        Object obj = map.get(key);
        System.out.println(Thread.currentThread().getName() + " successfully read");
    }
}
