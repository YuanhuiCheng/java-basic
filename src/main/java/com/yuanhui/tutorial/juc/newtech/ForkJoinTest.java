package com.yuanhui.tutorial.juc.newtech;

import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin
 * 并行执行任务，提高效率，大数据
 * 大数据： map reduce 把大任务拆成小任务
 * 工作窃取
 * <p>
 * 求和计算的任务：
 * 3000 （普通 sum）
 * 6000 （forkJoin）
 * 9000 （stream 并行流）
 * <p>
 * 如何使用 forkJoin
 * 1. forkJoinPool 通过它来执行
 * 2. 计算任务 forkJoinPool.execute(ForkJoinTask task)
 * 3. 计算类要继承 forkJoinTask
 */
public class ForkJoinTest extends RecursiveTask<Long> {
    private final Long start;
    private final Long end;
    private final Long threshold = 10000L;

    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {

    }

    @Override
    protected Long compute() {
        if (end - start < threshold) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 分支合并计算 forkJoin
            long middle = start + (end - start) / 2;
            ForkJoinTest task1 = new ForkJoinTest(start, middle);
            task1.fork(); // 拆分任务，把任务压入线程队列
            ForkJoinTest task2 = new ForkJoinTest(middle + 1, end);
            task2.fork(); // 拆分任务，把任务压入线程队列
            return task1.join() + task2.join();
        }
    }
}
