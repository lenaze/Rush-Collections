package com.javarush.task.task33.task3305;

public class RacingBike extends Motorbike {
    private String owner;
    private int age;

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public RacingBike() {
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RacingBike(String name, String owner, int age) {
        super(name);
        this.owner = owner;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "RacingBike{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", age=" + age +
                '}';
    }
}