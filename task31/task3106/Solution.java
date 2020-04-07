package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ZipInputStream zis;
        Arrays.sort(args, 1, args.length); //сортируем
        //создаем лист архивов в правильном порядке. Сразу создаем FIS
        List<FileInputStream> list = new ArrayList<>();
        for(int i = 1; i < args.length; i++){
            list.add(new FileInputStream(args[i]));
            System.out.println(args[i]);
        }
        //читаем все архивы
        zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(list)));
        FileOutputStream fos = new FileOutputStream(args[0]);  //поток записи
        byte[] buffer = new byte[1024 * 1024];
        while ((zis.getNextEntry()) != null) {
            int byteBuffer;
            while ((byteBuffer = zis.read(buffer)) != -1) {
                fos.write(buffer, 0, byteBuffer);
            }
            zis.closeEntry();
        }
        fos.close();
        zis.close();
    }
}
