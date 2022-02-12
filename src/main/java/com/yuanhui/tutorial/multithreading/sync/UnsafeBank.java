package com.yuanhui.tutorial.multithreading.sync;

public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "education funds");
        Drawing you = new Drawing(account, 50, "you");
        Drawing gf = new Drawing(account, 100, "imagination");

        you.start();
        gf.start();
    }
}

class Account {
    int money;
    String cardHolder;

    public Account(int money, String cardHolder) {
        this.money = money;
        this.cardHolder = cardHolder;
    }
}

class Drawing extends Thread {
    Account account;
    int drawnMoney;
    int remainingMoney;

    public Drawing(Account account, int drawnMoney, String name) {
        super(name);
        this.account = account;
        this.drawnMoney = drawnMoney;
    }

    @Override
    public void run() {
        // 锁变化的量，需要增删改的对象
        synchronized (account) {
            if (account.money - drawnMoney < 0) {
                System.out.println(Thread.currentThread().getName() + " money not enough, cannot withdraw");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.money = account.money - drawnMoney;
            remainingMoney += drawnMoney;

            System.out.println(account.cardHolder + "'s remaining amount is " + account.money);
            System.out.println(this.getName() + "'s remaining amount is " + remainingMoney);
        }
    }
}


