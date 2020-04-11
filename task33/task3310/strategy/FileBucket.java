package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try
        {
            path = Files.createTempFile("tmp", null);
            Files.deleteIfExists(path);
            Files.createFile(path);

        }
        catch (IOException e) { e.printStackTrace();}
        path.toFile().deleteOnExit();
    }

    public long getFileSize(){
        long size = 0;
        try { size = Files.size(path); } catch (IOException e) {}
        return size;
    }

    public void putEntry(Entry entry) {
        try(OutputStream fos = Files.newOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos)) {
            outputStream.writeObject(entry);
        }catch (IOException e) {}
    }

    public Entry getEntry(){
        Entry entry = null;
        if(getFileSize() > 0) {
            try {
                InputStream fis = Files.newInputStream(path);
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                Object object = inputStream.readObject();
                fis.close();
                inputStream.close();
                entry = (Entry)object;
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        }
        catch (IOException e) {}
    }

}
