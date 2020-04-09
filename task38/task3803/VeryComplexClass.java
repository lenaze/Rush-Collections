package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object x = new Integer(0);
        System.out.println((String)x);
    }

    public void methodThrowsNullPointerException() {
        ArrayList<Integer> list = null;
        list.get(20);
    }

    public static void main(String[] args) {
    }
}
