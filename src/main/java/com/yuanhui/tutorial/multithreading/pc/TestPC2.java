package com.yuanhui.tutorial.multithreading.pc;

/**
 * 生产者-消费者问题
 * 信号灯法：标志位解决
 */
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Audience(tv).start();
        new Actor(tv).start();
    }
}

// 生产者 -> 演员
class Actor extends Thread {
    TV tv;

    public Actor(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("friends");
            } else {
                this.tv.play("downton abbey");
            }
        }
    }
}

// 消费者 -> 观众
class Audience extends Thread {
    TV tv;

    public Audience(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

// 产品 -> 节目
class TV {
    // 演员表演，观众等待
    // 观众观看，演员等待
    String show;
    boolean flag = true;

    public synchronized void play(String show) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("actors play " + show);
        // 通知观众观看
        this.notifyAll();
        this.show = show;
        this.flag = !this.flag;
    }

    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("audiences watch " + show);
        // 通知演员表演
        this.notifyAll();
        this.flag = !this.flag;
    }
}