package com.example.cpp.DalInterfaces;

import com.example.cpp.model.MachineID;

import java.sql.SQLException;
import java.util.List;

public interface IMachineIDal {
    List<MachineID> GetAllMachineID() throws SQLException;


}
