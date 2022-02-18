package com.yuanhui.tutorial.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2 {
    // 注解的参数：参数类型 + 参数名 ();
    String name() default "";

    int age();

    int id() default -1; // 默认为-1，代表不存在

    String[] schools() default {"uw", "uoft"};
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3 {
    String value();
}

public class CustomizedAnnotation {
    @MyAnnotation2(name = "pepe", age = 12, schools = "uw")
    public void test() {
    }

    @MyAnnotation3("pepe")
    public void test2() {

    }
}
