package com.yuanhui.tutorial.reflection;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TablePepe {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldPepe {
    String columnName();

    String type();

    int length();
}

/**
 * 练习反射操作注解
 */
public class OrmDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("com.yuanhui.tutorial.reflection.Student2");
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        // 获得注解 value 的值
        TablePepe tablePepe = (TablePepe) c1.getAnnotation(TablePepe.class);
        String value = tablePepe.value();
        System.out.println(value);

        // 获得类指定的注解
        FieldPepe annotation = c1.getDeclaredField("name").getAnnotation(FieldPepe.class);
        System.out.println((annotation.columnName()));
        System.out.println(annotation.type());
        System.out.println(annotation.length());
    }
}

@TablePepe("db_student")
class Student2 {
    @FieldPepe(columnName = "db_id", type = "int", length = 10)
    private int id;
    @FieldPepe(columnName = "db_age", type = "int", length = 10)
    private int age;
    @FieldPepe(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public Student2() {
    }

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}