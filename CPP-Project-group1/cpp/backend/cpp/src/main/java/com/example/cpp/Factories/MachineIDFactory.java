package com.example.cpp.Factories;

import com.example.cpp.model.MachineID;

public class MachineIDFactory {

    public MachineIDFactory(){

    }

    public MachineID CreateMachineID(String machineId){
        MachineID machine = new MachineID(machineId);
        return machine;
    }



}
