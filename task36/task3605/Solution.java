package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        TreeSet<Character> set = new TreeSet<>();

        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
            int c;
            while (reader.ready()) {
                c = reader.read();
                if ((c > 64 && c < 91) || (c > 96 && c < 123)) {
                    set.add(Character.toLowerCase((char) c));
                }
            }
        }
        set.stream().limit(5).forEach(System.out::print);
    }
}
