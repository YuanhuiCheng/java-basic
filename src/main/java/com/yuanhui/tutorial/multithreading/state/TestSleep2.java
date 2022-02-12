package com.yuanhui.tutorial.multithreading.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep 时间指定当前线程阻塞的毫秒数。
 * sleep 时间达到后线程进入就绪状态。
 * sleep 可以模拟网络延时，倒计时等。
 * 每一个对象都有一个锁，sleep 不会释放锁。
 */
public class TestSleep2 {
    public static void main(String[] args) {
//        TestSleep2 testSleep2 = new TestSleep2();
//        try {
//            testSleep2.countDown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Date startTime = new Date(System.currentTimeMillis());
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void countDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) break;
        }
    }
}
