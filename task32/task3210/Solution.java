package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {
    public static void main(String... args) throws IOException {
        Path path = Paths.get(args[0]);
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        int length = text.length();
        RandomAccessFile file = new RandomAccessFile(String.valueOf(path), "rw");
        String fileString = "";

        byte[] b = new byte[length];
        file.seek(number);
        file.read(b, 0, length);
        fileString = new String(b);

        file.seek(file.length());
        if (text.equals(fileString))  {
            file.write("true".getBytes());
        }
        else {
            file.write("false".getBytes());
        }

        file.close();

    }

 /*   public static class MyFile{
        private Path path;

        public MyFile(Path path) {
            this.path = path;
        }

        public String getFromTo(int symbol, int length) throws IOException {
            RandomAccessFile file = new RandomAccessFile(String.valueOf(path), "r");
            String fileString = "";

            //file.seek(symbol);
            byte[] b = new byte[(int) file.length()];
            file.seek(symbol);
            file.read(b, 0, length);
            fileString = new String(b);
            //while((b = file.read()) != -1) {

            //    fileString = fileString.concat(String.valueOf((char)b));
            //}
            file.close();

            return fileString;
        }

        public void write(String text) throws IOException {
            RandomAccessFile file = new RandomAccessFile(String.valueOf(path), "rw");

                file.seek(file.length());
                file.write(text.getBytes());

            file.close();
        }

  */

    }

