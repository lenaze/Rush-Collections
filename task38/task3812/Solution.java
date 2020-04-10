package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.lang.reflect.AnnotatedArrayType;
import java.text.Annotation;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if(c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            String[] s = prepareMyTest.fullyQualifiedNames();
            Arrays.stream(s).forEach(System.out::println);
            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {
        if(c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Class<?>[] result = prepareMyTest.value();
            for (Class<?> cl : result) {
                System.out.println(cl.getSimpleName());
            }
            return true;
        }
        return false;
    }
}
