package com.yuanhui.tutorial.juc.newtech;

import java.util.function.Supplier;

public class SupplierInterfaceTest {
    public static void main(String[] args) {
//        Supplier supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return 1024;
//            }
//        };
        Supplier supplier = () -> {
            return 1024;
        };
        System.out.println(supplier.get());
    }
}
