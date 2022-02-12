package com.yuanhui.tutorial.multithreading.thread1;

/**
 * 模拟龟兔赛跑
 */
public class Race implements Runnable {
    private static String winner;

    public static void main(String[] args) {
        Race race = new Race();

        new Thread(race, "rabbit").start();
        new Thread(race, "turtle").start();
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (Thread.currentThread().getName().equals("rabbit")) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = gameOver(i);
            if (flag) break;
            System.out.println(Thread.currentThread().getName() + " runs for " + i + " steps");
        }
    }

    private boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        }
        if (steps >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("winner is " + winner);
            return true;
        }
        return false;
    }
}
