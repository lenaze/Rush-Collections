package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String track = br.readLine();
        br.close();

        Path path = Paths.get(track);
        if (!Files.isDirectory(path)) {
            System.out.println(String.format("%s - не папка", path.toAbsolutePath()));
        } else {
            MyVisitor visitor = new MyVisitor();
            Files.walkFileTree(path, visitor);
            System.out.println("Всего папок - " + visitor.getCountDir());
            System.out.println("Всего файлов - " + visitor.getCountFile());
            System.out.println("Общий размер - " + visitor.getCountByte());

        }
    }

         public static class MyVisitor extends SimpleFileVisitor<Path>{
            private int countDir = -1;
            private int countFile = 0;
            private long countByte = 0;

            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                countFile++;
                countByte += Files.size(path);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                countDir++;
                return FileVisitResult.CONTINUE;
            }

            int getCountDir(){ return countDir;}
            int getCountFile() {return countFile;}
            long getCountByte() {return countByte; }
        }
    }

