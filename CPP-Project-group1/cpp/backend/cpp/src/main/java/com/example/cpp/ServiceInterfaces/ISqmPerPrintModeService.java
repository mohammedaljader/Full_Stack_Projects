package com.example.cpp.ServiceInterfaces;

import com.example.cpp.model.SqmPerPrintMode;

import java.sql.SQLException;
import java.util.List;

public interface ISqmPerPrintModeService {
    List<SqmPerPrintMode> ListAllSqmPerPrintModeByTimeFrame(String startDate, String endDate) throws SQLException;
}
