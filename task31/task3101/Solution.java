package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
        public static void main(String[] args) throws IOException {


        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
            List<File> fileList = new ArrayList<>();

        try (PrintWriter writer = new PrintWriter(new FileWriter(allFilesContent, true))) {

            fileList = fillFileList(path.getPath());
            fileList.sort(Comparator.comparing(File::getName));

            for (File file : fileList) {
                BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                while (reader.ready()) {
                    writer.write(reader.readLine());
                }
                writer.write("\n");
                reader.close();
            }
        }
    }

    //Рекурсивно пробегаем поддиректории и заполняем список файлов
    private static List<File> fillFileList(String path) {
            List<File> fileList = new ArrayList<>();
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                fileList.addAll(fillFileList(file.getAbsolutePath()));
            }else {
                if (file.length() < 51)
                    fileList.add(file);
            }
        }
        return fileList;
    }
}
