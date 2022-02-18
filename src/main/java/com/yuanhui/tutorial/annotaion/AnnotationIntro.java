package com.yuanhui.tutorial.annotaion;

import java.util.ArrayList;
import java.util.List;

public class AnnotationIntro extends Object {
    @Deprecated
    public static void test() {
        System.out.println("deprecated");
    }

    public static void main(String[] args) {
        // test();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @SuppressWarnings("all")
    public void test2() {
        List list = new ArrayList<>();
    }
}
