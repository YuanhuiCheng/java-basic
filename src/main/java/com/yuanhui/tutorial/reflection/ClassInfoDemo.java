package com.yuanhui.tutorial.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassInfoDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("com.yuanhui.tutorial.reflection.User");

        User user = new User();
        c1 = user.getClass();

        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());

        System.out.println("---");
        Field[] fields = c1.getFields(); // 只能找到 public 属性
//        for (Field field : fields) {
//            System.out.println(field);
//        }

        fields = c1.getDeclaredFields(); // 能找到全部属性
        for (Field field : fields) {
            System.out.println(field);
        }

        Field name = c1.getDeclaredField("name");
        System.out.println(name);

        System.out.println("---");
        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            System.out.println("method: " + method);
        }

        methods = c1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("declared method: " + method);
        }

        System.out.println("---");
        // 获得指定方法
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        System.out.println("---");
        // 获得指定的构造器
        Constructor[] constructors = c1.getConstructors();
        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("constructor: " + constructor);
        }
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println("declared constructor: " + declaredConstructor);
        }

        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println(declaredConstructor);
    }
}
