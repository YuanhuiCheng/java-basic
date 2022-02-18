package com.yuanhui.tutorial.annotaion;

import java.lang.annotation.*;

// @Target 表示我们的注解可以用在哪些地方
@Target(value = {ElementType.METHOD, ElementType.TYPE})
// @Retention 表示我们的注解在哪些地方有效
// runtime > class > sources
@Retention(value = RetentionPolicy.RUNTIME)
// @Documented 表示是否将我们的注解生成在 Javadoc 中
@Documented
// 子类可以继承父类的注解
@Inherited
@interface MyAnnotation {

}

/**
 * 元注解：作用于注解上的注解
 */
@MyAnnotation
public class MetaAnnotation {
    @MyAnnotation
    public void test() {

    }
}
