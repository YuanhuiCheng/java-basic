package com.yuanhui.tutorial.reflection;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);

        /**
         * 1. 加载到内存，会产生一个类对应的 Class 对象；
         * 2. 链接，链接结束后 m = 0;
         * 3. 初始化 （合并代码）
         *      <clinit>() {
         *          System.out.println("A class 静态代码块初始化");
         *          m = 300;
         *          m = 100;
         *      }
         */
    }
}

class A {
    static {
        System.out.println("A class 静态代码块初始化");
        m = 300;
    }

    static int m = 100;

    public A() {
        System.out.println("A class 无参构造器");
    }
}