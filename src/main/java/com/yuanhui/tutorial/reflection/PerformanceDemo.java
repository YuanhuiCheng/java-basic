package com.yuanhui.tutorial.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PerformanceDemo {
    // 普通方式调用
    public static void normal() {
        User user = new User();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("normal way: " + (endTime - startTime) + " ms");
    }

    // 反射方式调用
    public static void reflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("reflective way: " + (endTime - startTime) + " ms");
    }

    // 反射方式调用，关闭检测
    public static void reflectAccessible() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);
        getName.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("reflective way with accessible: " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        normal();
        reflect();
        reflectAccessible();
    }
}
