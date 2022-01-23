package com.example.cpp.Factories;

import com.example.cpp.model.SqmPerPrintMode;
import com.example.cpp.model.Top10Machines;

import java.sql.Date;

public class Top10MachinesFactory {

    public Top10MachinesFactory(){
    }

    public Top10Machines createTop10Machines(String machine_id, int sum)
    {
        Top10Machines top10Machines = new Top10Machines(machine_id,sum);

        return top10Machines;
    }
}
