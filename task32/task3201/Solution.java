package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        MyFile file = new MyFile(Paths.get(args[0]));

        //String partOfFile = file.getFrom(number);
        file.writeFrom(number, text);
        //partOfFile = partOfFile.substring(text.length());
        //file.writeFrom(number, partOfFile);
    }

    public static class MyFile{
        private Path path;

        public MyFile(Path path) {
            this.path = path;
        }
        public String getFrom(int symbol) throws IOException {
            RandomAccessFile file = new RandomAccessFile(String.valueOf(path), "r");
            String fileString = "";

            file.seek(symbol);
            int b;
            while((b = file.read()) != -1) {
                fileString = fileString.concat(String.valueOf((char)b));
            }
            file.close();

            return fileString;
        }

        public void writeFrom(int symbol, String text) throws IOException {
            RandomAccessFile file = new RandomAccessFile(String.valueOf(path), "rw");

            if (file.length() > symbol) {
                file.seek(symbol);
                file.write(text.getBytes());
            }else {
                file.seek(file.length());
                file.write(text.getBytes());
            }

            file.close();
        }

    }
}
