package com.example.cpp.DalInterfaces;

import com.example.cpp.model.SqmPerPrintMode;
import com.example.cpp.model.Top10Machines;

import java.sql.SQLException;
import java.util.List;

public interface ITop10MachinesDal {
    List<Top10Machines> ListTop10Machines() throws SQLException;
}
