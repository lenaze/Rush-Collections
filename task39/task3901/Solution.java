package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if(s == null) return 0;

        int sizeMax = 0;
        String str = "";
        String letter;
        for (int i = 0; i < s.length(); i++) {
            letter = Character.toString(s.charAt(i));
            if (!str.contains(letter)) {
                str = str.concat(letter);
                sizeMax = Math.max(str.length(), sizeMax);
            }
            else str = str.substring(str.indexOf(letter));;
        }

        return sizeMax;
    }
}
