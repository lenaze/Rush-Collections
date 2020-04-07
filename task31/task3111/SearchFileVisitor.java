package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private List<Path> foundFiles = new ArrayList<>();
    private int maxSize;
    private int minSize;
    private String partOfContent;
    private String partOfName;

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        boolean containsName = true;
        if(partOfName != null && !file.getFileName().toString().contains(partOfName))
            containsName = false;

        boolean containsContent = true;
        String contentString = new String(content);
        if (partOfContent != null && !contentString.contains(partOfContent))
            containsContent = false;

        boolean minSizebool = true;
        if (minSize != 0 && content.length < minSize)
            minSizebool = false;

        boolean maxSizebool = true;
        if (maxSize != 0 && content.length > maxSize)
            maxSizebool = false;

        if (minSizebool && maxSizebool && containsContent && containsName)
            foundFiles.add(file);


        return FileVisitResult.CONTINUE;
    }
}
