package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        if (softReference == null)
            return null;

        //напишите тут ваш код
        return softReference.get();
    }

    public AnyObject put(Long key, AnyObject value) {
        try {
            if (cacheMap.containsKey(key))  {
                AnyObject m = cacheMap.get(key).get();
                SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
                softReference.clear();
                return m;
                }
            } catch (NullPointerException m) {
                SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
                softReference.clear();
                return null;
            }
        return null;
    }

    public AnyObject remove(Long key) {
        AnyObject o = null;
        if(!cacheMap.containsKey(key)) {
            return null;
        }else {
            o  = cacheMap.get(key).get();
            SoftReference<AnyObject> softReference = cacheMap.remove(key);
            softReference.clear();
            return o;
        }
    }
}