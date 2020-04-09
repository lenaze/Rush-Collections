package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(iterator.next() + " ");
            count++;
            if (count == 10) {
                break;
            }
        }


    }

    public class RoundIterator implements Iterator<T> {
        Iterator<T> it = Solution.super.iterator();
        @Override
        public boolean hasNext() {
            if(!it.hasNext()) { it = Solution.super.iterator(); }
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
}
