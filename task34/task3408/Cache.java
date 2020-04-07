package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private WeakHashMap<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        if (!cache.containsKey(key)){
            Constructor constructor = clazz.getConstructor(key.getClass());
            V obj = (V)constructor.newInstance(key);
            cache.put(key, obj);
            return obj;
        }
        return cache.get(key);

    }
    public boolean put(V obj) {
        //TODO add your code here
        int size = size();
        Class cl = obj.getClass();

        Method method = null;
        try {
            method = cl.getDeclaredMethod("getKey", null);
            method.setAccessible(true);
            K key = (K)method.invoke(obj, null);

            cache.put(key, obj);
            if (size() <= size){
                return false;
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return false;
        }


        return true;
    }

    public int size() {
        return cache.size();
    }
}
