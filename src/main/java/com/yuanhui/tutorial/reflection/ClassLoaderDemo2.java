package com.yuanhui.tutorial.reflection;

public class ClassLoaderDemo2 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取系统类加载器的父类加载器 -》 扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        // 获取扩展类加载器的父类加载器 -》 跟加载器 (c/c++)
        ClassLoader root = parent.getParent();
        System.out.println(root);

        ClassLoader classLoader = Class.forName("com.yuanhui.tutorial.reflection.ClassLoaderDemo2").getClassLoader();
        System.out.println(classLoader);

        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        // 如何获得系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
    }
}
