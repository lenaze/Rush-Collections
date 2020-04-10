package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = (int) Math.max(16, Math.ceil(collection.size()/.75f));
        map = new HashMap<>(capacity);
        for (E e : collection) {
            map.put(e, PRESENT);
        }
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            oos.defaultWriteObject();
            oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
            oos.writeInt(map.size());
            for (E e : map.keySet()) {
                oos.writeObject(e);
            }
        }catch (IOException e) {}
    }

    private void readObject(ObjectInputStream ois){
        try {
            ois.defaultReadObject();
            int capacity = ois.readInt();
            float loadFactor = ois.readFloat();
            int size = ois.readInt();
            map = new HashMap<>(capacity, loadFactor);

            for (int i = 0; i < size; i++) {
                E e = (E) ois.readObject();
                map.put(e, PRESENT);
            }
        }catch (IOException | ClassNotFoundException e) {}
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e, PRESENT);
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return null == map.remove(o);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Object clone() throws InternalError{
        AmigoSet amigoSet;
        try {
            amigoSet = (AmigoSet) super.clone();
            amigoSet.map = (HashMap) map.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
        return amigoSet;
    }
}
