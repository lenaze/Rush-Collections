package com.javarush.task.task35.task3507;


import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        Loader loader = new Loader();

        pathToAnimals = pathToAnimals.replace("%20", " ");
        if (!pathToAnimals.endsWith("/")) {
            pathToAnimals += "/";
        }

        File[] files = new File[0];
        try {
            files = new File(URLDecoder.decode(pathToAnimals, "UTF-8")).listFiles();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Set<Animal> animals = new HashSet<>();
        for(File f: files) {
            if (f.getName().endsWith(".class")) {
                try {
                    Class clazz = loader.findClass(f.getAbsolutePath());
                    Constructor c = clazz.getConstructor();
                    if (c.getModifiers() == Modifier.PUBLIC && Animal.class.isAssignableFrom(clazz) && c.getParameterCount() == 0) {
                        c.setAccessible(true);
                        animals.add((Animal) c.newInstance());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return animals;
    }

    static class Loader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                Path file = Paths.get(name);
                byte[] bytes = Files.readAllBytes(file);
                return defineClass(null, bytes, 0, bytes.length);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return super.findClass(name);
            }
        }
    }

