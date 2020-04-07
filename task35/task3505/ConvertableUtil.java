package com.javarush.task.task35.task3505;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <Key, Convertable> Map<Key, Convertable> convert(List<? extends Convertable> list) {
        Map<Key, Convertable> result = new HashMap<Key, Convertable>();
        for (Convertable l :
                list) {
            try {
                Method method = l.getClass().getMethod("getKey");
                method.setAccessible(true);
                result.put((Key) method.invoke(l), l);
            }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {}
        }
        return result;
    }
}
