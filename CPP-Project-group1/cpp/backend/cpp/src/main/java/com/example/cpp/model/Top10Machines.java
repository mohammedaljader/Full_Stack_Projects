package com.example.cpp.model;

public class Top10Machines {
    private String machineID;
    private int sum;

    public Top10Machines(String machineID, int sum) {
        this.machineID = machineID;
        this.sum = sum;
    }

    public String getMachineID() {
        return machineID;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
