package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

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
            try {
                if (isImplement(c) && Modifier.isStatic(c.getModifiers()) && Modifier.isPrivate(c.getModifiers())) {
                    Method method = c.getMethod("get", int.class);
                    method.setAccessible(true);
                    Constructor constructor = c.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    if (constructor != null)
                        method.invoke(constructor.newInstance(), 0);
                }
            }
            catch (NoSuchMethodException | IllegalAccessException | InstantiationException e) {}
            catch (InvocationTargetException e) {
                if (e.getCause().toString().contains("IndexOutOfBoundsException"))
                    return c;
            }
        }
        return null;
    }

    public static boolean isImplement(Class<?> c){
        return List.class.isAssignableFrom(c) || List.class.isAssignableFrom(c.getSuperclass());
    }
}
