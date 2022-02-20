package com.yuanhui.tutorial.juc.newtech;

import java.util.function.Predicate;

/**
 * 断定型接口：
 * 有一个输入参数，返回值只能是布尔值
 */
public class PredicateInterfaceTest {
    public static void main(String[] args) {
        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };
        Predicate<String> predicate = (str) -> {
            return str.isEmpty();
        };
        System.out.println(predicate.test("1r23"));
    }
}
