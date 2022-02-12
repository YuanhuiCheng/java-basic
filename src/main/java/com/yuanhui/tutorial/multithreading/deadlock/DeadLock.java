package com.yuanhui.tutorial.multithreading.deadlock;

public class DeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "alice");
        Makeup g2 = new Makeup(1, "abby");

        g1.start();
        g2.start();
    }
}

class Lipstick {

}

class Mirror {

}

class Makeup extends Thread {
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girlName;

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 化妆，互相持有对方的锁/资源
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girlName + " gets lock of lipstick");
                Thread.sleep(1000);

                synchronized (mirror) {
                    System.out.println(this.girlName + " gets lock of mirror");
                }
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girlName + " gets lock of mirror");
                Thread.sleep(2000);

                synchronized (lipstick) {
                    System.out.println(this.girlName + " gets lock of lipstick");
                }
            }
        }
    }
}
