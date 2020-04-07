package com.javarush.task.task36.task3601;

import java.util.List;

public class Model {
    public List<String> getStringDataList() {
        Service service = new Service();

        return service.getData();
    }
}
