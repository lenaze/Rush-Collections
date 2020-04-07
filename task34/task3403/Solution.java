package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        int m = 2;
        while (m <= n) {
            if (n % m == 0) {
                System.out.print(m + " ");
                if (m == n)
                    return;
                recurse(n / m);
                break;
            }
            m++;
        }
    }
}
