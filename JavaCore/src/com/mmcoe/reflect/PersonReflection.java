package com.mmcoe.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PersonReflection {

    public static void main(String[] args) throws Exception {

        Person p1 = new Person("Polo", 21);

        System.out.println(p1);
        p1.print();

        Class pc = p1.getClass();

        System.out.println("Class Name: " + pc.getName());
        System.out.println("Superclass: " + pc.getSuperclass());

        Constructor [] constructors = pc.getConstructors();

        System.out.println("\n--- List of Constructors ---");
        for (Constructor c : constructors) {
            System.out.println(c);
        }

        Method[] methods = pc.getDeclaredMethods();

        System.out.println("\n--- Declared Methods ---");
        for (Method m : methods) {
            System.out.println(m);
        }

        Field[] fields = pc.getFields();

        System.out.println("\n--- Public Fields ---");
        for (Field f : fields) {
            System.out.println(f);
        }
    }
}