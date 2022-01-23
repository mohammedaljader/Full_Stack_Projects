package com.example.cpp.Factories;

import com.example.cpp.model.InkUsage;

import java.sql.Date;

public class InkUsageFactory {

    public InkUsageFactory(){
    }

    public InkUsage createInkUsage(String machine_id, Date date, float color_black, float color_cyan, float color_magenta, float color_yellow)
    {
        InkUsage inkUsage = new InkUsage(machine_id,date,color_black,color_cyan,color_magenta,color_yellow);

        return  inkUsage;
    }

}
