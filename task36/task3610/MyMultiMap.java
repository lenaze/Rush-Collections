package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry<K, List<V>> m : map.entrySet()) {
            count += m.getValue().size();
        }
        return count;
    }

    @Override
    public V put(K key, V value) {
        if (!map.containsKey(key)) {
            List<V> ls = new ArrayList<V>();
            ls.add(value);
            map.put(key, ls);
            return null;
        }
        V v = map.get(key).get(map.get(key).size() - 1);

        if (map.get(key).size() < repeatCount) {
            map.get(key).add(value);
        } else if (map.get(key).size() == repeatCount) {
            map.get(key).remove(0);
            map.get(key).add(value);
        }
        return v;
    }

    @Override
    public V remove(Object key) {
        if (!map.containsKey(key)) return null;

        List<V> ls = map.get(key);
        V v = ls.get(0);
        ls.remove(0);

        if (ls.size() == 0) {
            map.remove(key);
        } else {
            map.put((K) key, ls);
        }
        return v;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> collection = new ArrayList<>();
        for (Map.Entry<K, List<V>> m: map.entrySet()) {
            if (m.getValue() != null)
                collection.addAll(m.getValue());
        }
        return collection;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        boolean bool = false;
        for (Map.Entry<K, List<V>> m : map.entrySet()) {
            if (m.getValue().contains(value)) bool = true;
        }
        return bool;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}