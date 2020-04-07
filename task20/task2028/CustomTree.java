package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    List<Entry<String>> list = new ArrayList<>();
    int removed = 0;

    public CustomTree() {
        root = new Entry<>("0");
        root.parent = null;
        list.add(root);
    }

    //Look for the place to add an element s
    public boolean add(String s) {

       if (list.size() == 0) {
           list.add(root);
       }else{
           for (int i = 0; i < list.size(); i++) {
               if(list.get(i).isAvailableToAddChildren()){
                   if (list.get(i).availableToAddLeftChildren){
                       list.get(i).leftChild = new Entry<>(s);
                       list.get(i).leftChild.parent = list.get(i);
                       list.get(i).availableToAddLeftChildren = false;
                       list.add(list.get(i).leftChild);
                       return true;
                   }else if (list.get(i).availableToAddRightChildren){
                       list.get(i).rightChild = new Entry<>(s);
                       list.get(i).rightChild.parent = list.get(i);
                       list.get(i).availableToAddRightChildren = false;
                       list.add(list.get(i).rightChild);
                       return true;
                   }
               }
           }
       }
        return false;
    }


    //Count of the elements in the tree right now
    @Override
    public int size() {
        int size = 0;
       // if (removed == 1) {
            size = list.size() - 1;
      //  } else if (removed >=2 ) {
       //    size = list.size() / 2 + 1;
       // }
        return size;
    }

    //Get the parent of an element with the name s
    public String getParent(String s){
        String elementName = null;
        for (Entry<String> entry :
                list) {
            if(entry.elementName.equals(s))
                elementName = entry.parent.elementName;
        }
        return elementName;
    }

    public boolean remove(Object o){
        boolean bool = false;
        Entry<String> entry;
        if(!(o instanceof String)){
            throw new UnsupportedOperationException();
        }else{
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).elementName.equals(o)){
                    entry = list.get(i);
                    if(entry.leftChild != null) remove(entry.leftChild.elementName);
                    if(entry.rightChild != null) remove(entry.rightChild.elementName);
                    list.remove(entry);
                    entry.parent.deleteChild(entry.elementName);
                    removed++;
                }
            }
            for (Entry<String> e :
                    list) {
                bool = bool | e.isAvailableToAddChildren();
            }
            if (!bool){
                letMeHaveAParent();
            }
        }
        return true;
    }

    public void letMeHaveAParent(){
        for (Entry<String> entry :
                list) {
            if (entry.leftChild == null) {
                entry.availableToAddLeftChildren = true;
            }
            if (entry.rightChild == null) {
                entry.availableToAddRightChildren = true;
            }
        }
    }


    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }


    public String set(int index, String element){throw new UnsupportedOperationException();}
    public void add(int index, String element){throw new UnsupportedOperationException();}
    public String remove(int index){throw new UnsupportedOperationException();}
    public List<String> subList(int fromIndex, int toIndex){throw new UnsupportedOperationException();}
    protected void removeRange(int fromIndex, int toIndex){throw new UnsupportedOperationException();}
    public boolean addAll(int index, Collection<? extends String> c){throw new UnsupportedOperationException();}

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren(){

            return availableToAddLeftChildren | availableToAddRightChildren;
        }

        public void deleteChild(String elementName){
            if (this.leftChild != null && leftChild.elementName.equals(elementName)) {
                leftChild = null;
                //availableToAddLeftChildren = true;
            }
            else if (this.rightChild != null && rightChild.elementName.equals(elementName)) {
                rightChild = null;
                //availableToAddRightChildren = true;
            }
        }
    }

}
