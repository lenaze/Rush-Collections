package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null){
            return "Hello";
        }else {
            String str = "";
            BufferedReader reader1 = new BufferedReader(reader);
            String line;
            while ((line = reader1.readLine()) != null){
                str = str.concat(line);
            }

            char[]c = new char[str.length()];
            for (int i = 0; i < str.length(); i++) {
                c[i] = (char) ((int)str.toCharArray()[i] + key);
            }
            str = new String(c);

            return str;
        }
    }
}
