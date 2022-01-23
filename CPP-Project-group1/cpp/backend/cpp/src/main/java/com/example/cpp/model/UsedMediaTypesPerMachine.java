package com.example.cpp.model;

public class UsedMediaTypesPerMachine {
    private String machineID;
    private String name;
    private int area;

    public UsedMediaTypesPerMachine(String machineID, String name, int area) {
        this.machineID = machineID;
        this.name = name;
        this.area = area;
    }

    public String getMachineID() {
        return machineID;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
