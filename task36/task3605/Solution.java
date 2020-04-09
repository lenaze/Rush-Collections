package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));

        TreeSet<Character> set = new TreeSet<>();
        while(reader.ready()){
            set.add((char) reader.read());
        }
        reader.close();




    }
}
