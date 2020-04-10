package com.javarush.task.task37.task3711;

public class Computer {
    CPU cpu = new CPU();
    HardDrive hardDrive = new HardDrive();
    Memory memory = new Memory();

    public void run(){
        cpu.calculate();
        hardDrive.storeData();
        memory.allocate();
    }
}
