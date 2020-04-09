package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));

        List<Character> list = new ArrayList<>();
        while(reader.ready()){
            list.add((char) reader.read());
        }
        reader.close();


    }
}
