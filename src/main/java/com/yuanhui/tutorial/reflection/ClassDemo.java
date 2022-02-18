package com.yuanhui.tutorial.reflection;

public class ClassDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("this person is " + person.name);

        Class c1 = person.getClass();
        System.out.println(c1.hashCode());

        Class<?> c2 = Class.forName("com.yuanhui.tutorial.reflection.Student");
        System.out.println(c2.hashCode());

        Class<Student> c3 = Student.class;
        System.out.println(c3.hashCode());

        // 基本内置类型的包装类都有一个 type 属性
        Class<Integer> c4 = Integer.TYPE;
        System.out.println(c4);

        // 获得父类类型
        Class c5 = c1.getSuperclass();
        System.out.println(c5);
    }
}

class Person {
    String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {
    public Student() {
        this.name = "pepe";
    }
}

class Teacher extends Person {
    public Teacher() {
        this.name = "pepe's teacher";
    }
}
