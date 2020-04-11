package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    static enum HumanFactoryType{
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType type){
        AbstractFactory factory = null;
        if (type == HumanFactoryType.MALE) factory = new MaleFactory();
        if (type == HumanFactoryType.FEMALE) factory = new FemaleFactory();
        return factory;
    }
}
