package com.example.cpp.ServiceInterfaces;

import com.example.cpp.model.InkUsage;

import java.sql.SQLException;
import java.util.List;

public interface IInkUsageService {

    List<InkUsage> getInkUsageByTime() throws SQLException;
    List<InkUsage> getInkUsageByTimeFrame(String startDate, String endDate) throws  SQLException;
    List<InkUsage> FilterInkUsageBasedOnMachineIDs(List<String> machineIds, String startDate, String endDate) throws SQLException;

}
