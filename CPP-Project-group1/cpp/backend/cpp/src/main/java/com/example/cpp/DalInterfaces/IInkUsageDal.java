package com.example.cpp.DalInterfaces;

import com.example.cpp.model.InkUsage;

import java.sql.SQLException;
import java.util.List;

public interface IInkUsageDal {
    List<InkUsage> ListOfInkUsage() throws SQLException;
    List<InkUsage> ListInkUsageByTimeFrame(String startDate, String endDate) throws  SQLException;
    List<InkUsage> FilterInkUsageBasedOnMachineIDs() throws SQLException;
}
