package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] classes = Collections.class.getDeclaredClasses();

        for (Class<?> c : classes) {
            try{
                if (Modifier.isStatic(c.getModifiers()) && Modifier.isPrivate(c.getModifiers())){
                    Method method = c.getMethod("get");
                    method.setAccessible(true);
                    Constructor<?> constructor = c.getDeclaredConstructor();
                    constructor.setAccessible(true);

                }

            }catch (NullPointerException | NoSuchMethodException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
