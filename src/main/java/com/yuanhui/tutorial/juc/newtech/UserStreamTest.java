package com.yuanhui.tutorial.juc.newtech;

import java.util.Arrays;
import java.util.List;

/**
 * 题目要求：只能用一行代码实现。
 * 筛选:
 * 1. id 必须是偶数；
 * 2. 年龄必须大于23岁；
 * 3. 用户名转为大写字母；
 * 4. 用户名字母倒着排序；
 * 5，只输出一个用户；
 */
public class UserStreamTest {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 20);
        User u2 = new User(2, "b", 21);
        User u3 = new User(3, "c", 22);
        User u4 = new User(4, "d", 23);
        User u5 = new User(5, "e", 24);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream().filter(u -> {
            return u.getId() % 2 == 0;
        }).filter(u -> {
            return u.getAge() > 20;
        }).map(u -> {
            return u.getName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o1.compareTo(o2);
        }).limit(1).forEach(System.out::println);
    }
}
