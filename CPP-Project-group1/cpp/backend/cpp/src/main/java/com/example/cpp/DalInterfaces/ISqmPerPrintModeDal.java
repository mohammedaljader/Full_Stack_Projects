package com.example.cpp.DalInterfaces;

import com.example.cpp.model.InkUsage;
import com.example.cpp.model.SqmPerPrintMode;

import java.sql.SQLException;
import java.util.List;

public interface ISqmPerPrintModeDal {
    List<SqmPerPrintMode> ListAllSqmPerPrintModeByTimeFrame(String startDate, String endDate) throws SQLException;
}
