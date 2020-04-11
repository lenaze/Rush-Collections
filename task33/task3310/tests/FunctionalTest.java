package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String first = "text";
        String second = "not the same text";
        String third = "text";

        long idF = shortener.getId(first);
        long idS = shortener.getId(second);
        long idT = shortener.getId(third);

        Assert.assertNotEquals(idF, idS);
        Assert.assertEquals(idT, idF);

        Assert.assertEquals(shortener.getString(idF), first);
        Assert.assertEquals(shortener.getString(idS), second);
        Assert.assertEquals(shortener.getString(idT), third);
    }

        @Test
        public void testHashMapStorageStrategy(){
            Shortener shortener = new Shortener(new HashMapStorageStrategy());
            testStorage(shortener);
        }

        @Test
        public void testOurHashMapStorageStrategy(){
            Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
            testStorage(shortener);
        }

        @Test
        public void testFileStorageStrategy(){
            Shortener shortener = new Shortener(new FileStorageStrategy());
            testStorage(shortener);
        }

        @Test
        public void testHashBiMapStorageStrategy(){
            Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
            testStorage(shortener);
        }

        @Test
        public void testDualHashBidiMapStorageStrategy(){
            Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
            testStorage(shortener);
        }

        @Test
        public void testOurHashBiMapStorageStrategy(){
            Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
            testStorage(shortener);
        }
}
