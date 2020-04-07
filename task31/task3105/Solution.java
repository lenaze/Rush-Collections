package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        HashMap<ZipEntry, ByteArrayOutputStream> zipfiles = null;
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]))) {

            zipfiles = new HashMap<>();
            ZipEntry zipEntry;
            int length;
            while ((zipEntry = zis.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                while ((length = zis.read(bytes)) != -1) {
                    byteArrayOutputStream.write(bytes, 0, length);
                }
                System.out.println(zipEntry);
                zipfiles.put(zipEntry, byteArrayOutputStream);
            }
            
        }catch (Exception e) {e.printStackTrace();}

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]))) {

            File fileNameAdded = new File(args[0]);
            String fileNameSave = "new/" + fileNameAdded.getName();
            //записываем файл
            zos.putNextEntry(new ZipEntry(fileNameSave));
            Files.copy(fileNameAdded.toPath(), zos);
            zos.closeEntry();
            //запись всех остальных файлов
            for (Map.Entry<ZipEntry, ByteArrayOutputStream> entry : zipfiles.entrySet()) {
                if (entry.getKey().getName().compareToIgnoreCase(fileNameSave) != 0) {
                    zos.putNextEntry(new ZipEntry(entry.getKey().getName()));
                    zos.write(entry.getValue().toByteArray());
                    zos.closeEntry();
                }
            }
        }catch (Exception e) {}
    }
}
