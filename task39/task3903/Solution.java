package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        String bit_num = Long.toBinaryString(number);
        StringBuilder sb = new StringBuilder(bit_num);

        sb.reverse();
        for (int i2 = sb.length(); i2 < 64; i2++) {
            sb.append("0");
        }

        String tmpi = sb.substring(i, i + 1);
        String tmpj = sb.substring(j, j + 1);
        sb.replace(j, j + 1, tmpi);
        sb.replace(i, i + 1, tmpj);
        sb.reverse();

        number = Long.parseLong(sb.toString(), 2);
        return number;
    }
}
