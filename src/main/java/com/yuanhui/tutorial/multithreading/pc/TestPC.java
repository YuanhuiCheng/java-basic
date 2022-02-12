package com.yuanhui.tutorial.multithreading.pc;

/**
 * 生产者-消费者问题
 * 利用缓冲区解决：管程法
 */
public class TestPC {
    public static void main(String[] args) {
        SyncContainer container = new SyncContainer();

        new Producer(container).start();
        new Consumer(container).start();
    }
}

class Producer extends Thread {
    SyncContainer container;

    public Producer(SyncContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("produce chicken " + i);
            container.push(new Chicken(i));
        }
    }
}

class Consumer extends Thread {
    SyncContainer container;

    public Consumer(SyncContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("consume chicken " + container.pop().id);
        }
    }
}

class Chicken {
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

class SyncContainer {
    Chicken[] chickens = new Chicken[10];
    int cnt = 0;

    // 生产者放入产品
    public synchronized void push(Chicken chicken) {
        if (cnt == chickens.length) {
            // 通知消费者消费， 生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chickens[cnt++] = chicken;

        // 通知消费者消费
        this.notifyAll();
    }

    // 消费者消费产品
    public synchronized Chicken pop() {
        if (cnt == 0) {
            // 等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Chicken chicken = chickens[--cnt];
        this.notifyAll();
        return chicken;
    }
}
