package com.yuanhui.tutorial.juc.newtech;

import java.util.function.Function;

/**
 * Function 函数型接口:
 * 有一个输入参数，有一个输出。
 * 只要是函数型接口，可以用 lambda 表达式简化。
 */
public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        // 工具方法/类，输出输入的值
//        Function function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };

        // 函数式接口
        Function function = (str) -> {
            return str;
        };
        System.out.println(function.apply(3));
    }
}
