package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
            Set<Long> set = new HashSet<>();
        for (String s : strings) { set.add(shortener.getId(s)); }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> set = new HashSet<>();
        for (Long l : keys) { set.add(shortener.getString(l)); }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        //6.2.3.1
        System.out.println(strategy.getClass().getSimpleName());
        //6.2.3.2
        Set<String> strings = new HashSet<>();
        Set<Long> resultKeys;
        Set<String> resultStrings;
        for (long i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        //6.2.3.3
        Shortener shortener = new Shortener(strategy);
        //6.2.3.4
        Date startDate = new Date();
        resultKeys = getIds(shortener, strings);
        Date endDate = new Date();
        long resultTime = endDate.getTime() - startDate.getTime();
        //6.2.3.5
        Helper.printMessage(Long.toString(resultTime));
        startDate = new Date();
        resultStrings = getStrings(shortener, resultKeys);
        endDate = new Date();
        resultTime = endDate.getTime() - startDate.getTime();
        //6.2.3.6
        Helper.printMessage(Long.toString(resultTime));
        if (strings.equals(resultStrings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }

    }

}
