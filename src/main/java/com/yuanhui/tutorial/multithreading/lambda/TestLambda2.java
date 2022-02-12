package com.yuanhui.tutorial.multithreading.lambda;

interface ILove {
    void love(int a);
}

public class TestLambda2 {
    public static void main(String[] args) {
        ILove iLove = (int a) -> {
            System.out.println("i love u -> " + a);
        };
        iLove = (a) -> {
            System.out.println("i love u -> " + a);
        };
        iLove = a -> {
            System.out.println("i love u -> " + a);
        };
        iLove = a -> System.out.println("i love u -> " + a);
        iLove.love(2);
    }
}


