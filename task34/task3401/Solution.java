package com.javarush.task.task34.task3401;

/* 
Числа Фибоначчи с помощью рекурсии
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.fibonacci(9));     //34
        System.out.println(solution.fibonacci(5));     //5
        System.out.println(solution.fibonacci(2));     //1
        System.out.println(solution.fibonacci(1));     //1
    }

    public int fibonacci(int n) {
        int k = 0;
        if(n == 0) k = 0;
        else if (n == 1) k = 1;
        else if (n > 1)
        k = fibonacci(n-1)+fibonacci(n-2);
        return k;
    }
}
