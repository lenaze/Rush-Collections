package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
                    method.invoke(constructor.newInstance(),"Hello");
                }
            }catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }catch (IndexOutOfBoundsException e){
                return c;
            }
        }
        return null;
    }
}
