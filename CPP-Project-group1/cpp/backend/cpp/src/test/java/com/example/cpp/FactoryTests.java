package com.example.cpp;

import com.example.cpp.Factories.InkUsageFactory;
import com.example.cpp.Factories.MachineIDFactory;
import com.example.cpp.Factories.MediaCategoryFactory;
import com.example.cpp.model.InkUsage;
import com.example.cpp.model.MachineID;
import com.example.cpp.model.MediaCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

public class FactoryTests {

    private MediaCategoryFactory mediaCategoryFactory = new MediaCategoryFactory();
    private InkUsageFactory inkUsageFactory = new InkUsageFactory();
    private MachineIDFactory machineIDFactory = new MachineIDFactory();

    @Test
    public void MediaCategoryFactoryTest(){
        Date testDate = new Date(2020,11,11);

        MediaCategory mediaCategoryTest = mediaCategoryFactory.createMediaCategory("3",testDate,10f,5f,2f,
                0f,0f,0f,0f,0f,0f,0f,0f);

        assertEquals(10f, mediaCategoryTest.getFilm());


    }

    @Test
    public void InkUsageTest(){
        Date testDate = new Date(2020,11,11);

        InkUsage inkUsage = inkUsageFactory.createInkUsage("3",testDate,10f,5f,6f,7f);

        assertEquals(10f, inkUsage.getColor_black());


    }

    @Test
    public void MachineIDTest(){
        Date testDate = new Date(2020,11,11);

        MachineID machineID = machineIDFactory.CreateMachineID("3");

        assertEquals("3", machineID.getMachineID());


    }











}
