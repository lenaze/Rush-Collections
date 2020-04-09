package com.javarush.task.task36.task3606;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        String sep = System.getProperty("file.separator");
        String pathName = packageName;
        if(!(packageName.endsWith(sep))){
            pathName = pathName.concat(sep);
        }

        File[] files = new File(pathName).listFiles((folder, name) -> name.endsWith(".class"));

        MyLoader loader;
        for (File f : files) {
         loader = new MyLoader();
         hiddenClasses.add(loader.findClass(pathName+f.getName()));
        }
    }

    public class MyLoader extends ClassLoader{

        public Class<?> findClass(String path) throws ClassNotFoundException {
            Class<?> c = null;
            try(FileInputStream fis = new FileInputStream(path)) {
                byte[] b = new byte[(int) new File(path).length()];
                fis.read(b, 0, b.length);
                fis.close();
                c = defineClass(null, b, 0, b.length);
            } catch (ClassFormatError | IOException classFormatError) { super.findClass(path);}
            return c;
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class cl : hiddenClasses) {
            if (cl.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                Constructor[] constructors = cl.getDeclaredConstructors();
                for (Constructor c : constructors) {
                    if (c.getParameterCount() == 0) {
                        c.setAccessible(true);
                        try {
                            return (HiddenClass) c.newInstance();
                        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        }
                    }
                }
            }
        }
        return null;
    }
}

