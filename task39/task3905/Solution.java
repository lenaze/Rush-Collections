package com.javarush.task.task39.task3905;

/* 
Залей меня полностью
*/

import static com.javarush.task.task39.task3905.Color.*;;

public class Solution {
    public static void main(String[] args) {
        Color[][] image = new Color[][] {
                {BLUE, BLUE, BLUE, BLUE}
        };

        new PhotoPaint().paintFill(image, 2, 0, ORANGE);

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.println(image[i][j]);
            }
        }

    }
}
