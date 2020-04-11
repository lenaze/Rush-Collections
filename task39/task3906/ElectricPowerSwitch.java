package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {
    Switchable switchable = null;

    public ElectricPowerSwitch(Switchable switchable) {
        this.switchable = switchable;
    }

    public void press() {
        System.out.println("Power switch flipped.");
        if (switchable.isOn()) {
            switchable.turnOff();
        } else {
            switchable.turnOn();
        }
    }
}
