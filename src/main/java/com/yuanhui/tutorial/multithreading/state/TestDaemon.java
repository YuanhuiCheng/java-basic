package com.yuanhui.tutorial.multithreading.state;

public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);

        thread.start(); // 上帝守护线程启动

        new Thread(you).start(); // 用户线程启动
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("god bless u");
        }
    }
}

class You implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("happy life...");
        }
        System.out.println("goodbye world");
    }
}
