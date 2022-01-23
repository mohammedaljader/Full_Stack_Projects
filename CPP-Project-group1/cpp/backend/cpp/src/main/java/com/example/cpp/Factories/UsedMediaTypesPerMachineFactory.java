package com.example.cpp.Factories;

import com.example.cpp.model.Top10Machines;
import com.example.cpp.model.UsedMediaTypesPerMachine;

public class UsedMediaTypesPerMachineFactory {
    public UsedMediaTypesPerMachineFactory(){
    }

    public UsedMediaTypesPerMachine createUsedMediaTypes(String machine_id, String name, int area)
    {
        UsedMediaTypesPerMachine usedMediaTypesPerMachine = new UsedMediaTypesPerMachine(machine_id,name,area);

        return usedMediaTypesPerMachine;
    }
}
