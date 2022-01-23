package com.example.cpp.ServiceInterfaces;

import com.example.cpp.model.MachineID;

import java.sql.SQLException;
import java.util.List;

public interface IMachineIDService {
    List<MachineID> GetAllMachineID() throws SQLException;




}
