package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {

    }

    public static int maxSquare(int[] [] matrix) {
        if (matrix.length == 0) return 0;
        int[][] cache = Arrays.copyOf(matrix, matrix.length);

        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0 && !(i == 0 || j == 0)) {
                    cache[i][j] = 1 + Math.min(cache[i - 1][j], Math.min(cache[i - 1][j - 1], cache[i][j - 1]));
                }

                if (cache[i][j] > result) result = cache[i][j];
            }
        }
        return result*result;
    }
}
