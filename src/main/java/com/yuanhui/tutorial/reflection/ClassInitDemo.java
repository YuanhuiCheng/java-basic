package com.yuanhui.tutorial.reflection;

public class ClassInitDemo {
    static {
        System.out.println("Main class is loaded");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 1. 主动引用
        // Son son = new Son();

        // 反射也会产生主动引用
        // Class.forName("com.yuanhui.tutorial.reflection.Son");

        /**
         * 当通过子类引用父类的静态变量，不会导致子类初始化；
         * 通过数组定义类引用，不会触发此类的初始化；
         * 引用常量不会触发此类的初始化 (常量在链接阶段就存入调用类的常量池中了)；
         */
        // System.out.println(Son.b);

        // Son[] sonArr = new Son[5];

        System.out.println(Son.M);
    }
}

class Father {
    static int b = 2;

    static {
        System.out.println("Super class is loaded");
    }
}

class Son extends Father {
    static final int M = 1;
    static int m = 100;

    static {
        System.out.println("Sub class is loaded");
        m = 300;
    }
}
