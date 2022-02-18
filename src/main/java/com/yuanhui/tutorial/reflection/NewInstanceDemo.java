package com.yuanhui.tutorial.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射动态创建对象
 */
public class NewInstanceDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class c1 = Class.forName("com.yuanhui.tutorial.reflection.User");

        // 调用的是无参构造器
        User user = (User) c1.getDeclaredConstructor().newInstance();
        System.out.println(user);

        // 通过构造器创建对象
        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User pepe = (User) constructor.newInstance("pepe", 1, 12);
        System.out.println(pepe);

        // 通过反射调用普通方法
        User pepe2 = (User) c1.getDeclaredConstructor().newInstance();
        // 通过反射获取方法
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(pepe2, "little pepe");

        System.out.println(pepe2.getName());

        // 通过反射操作属性
        User pepe3 = (User) c1.getDeclaredConstructor().newInstance();
        Field name = c1.getDeclaredField("name");
        // 不能直接操作私有属性，需要关闭安全检测
        name.setAccessible(true);
        name.set(pepe3, "cute pepe");
        System.out.println(pepe3.getName());
    }
}
