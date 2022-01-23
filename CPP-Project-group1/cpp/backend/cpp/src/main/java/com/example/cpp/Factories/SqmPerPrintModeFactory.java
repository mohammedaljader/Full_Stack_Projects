package com.example.cpp.Factories;

import com.example.cpp.model.InkUsage;
import com.example.cpp.model.SqmPerPrintMode;

import java.sql.Date;

public class SqmPerPrintModeFactory {

    public SqmPerPrintModeFactory(){
    }

    public SqmPerPrintMode createSqmPerPrintMode(int machine_id, Date date, float pass_highDensity_1, float pass_1, float pass_2, float pass_4, float pass_8, float pass_highDensity_8, float pass_16,float other)
    {
        SqmPerPrintMode sqmPerPrintMode = new SqmPerPrintMode(machine_id,date,pass_highDensity_1,pass_1,pass_2,pass_4,pass_8,pass_highDensity_8,pass_16,other);

        return sqmPerPrintMode;
    }
}
