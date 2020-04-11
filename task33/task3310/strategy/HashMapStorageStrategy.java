package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorageStrategy implements StorageStrategy{
    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        Long key = 0L;
        for (Map.Entry<Long, String> m : data.entrySet()) {
            if (m.getValue().equals(value)) key = m.getKey();
        }
        return key;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
