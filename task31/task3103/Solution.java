package com.javarush.task.task31.task3103;

import java.io.IOException;//
import java.util.List;//

/* 
Своя реализация
*/
public class Solution {
    public static byte[] readBytes(String fileName) throws IOException {
         return java.nio.file.Files.readAllBytes(new java.io.File(fileName).toPath());
    }

    public static List<String> readLines(String fileName) throws IOException {
        return java.nio.file.Files.readAllLines(new java.io.File(fileName).toPath());
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        java.nio.file.Files.write(java.nio.file.Paths.get(fileName), bytes);
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
        java.nio.file.Files.copy(java.nio.file.Paths.get(resourceFileName), java.nio.file.Paths.get(destinationFileName));
    }
}
