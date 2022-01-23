package com.example.cpp.DalInterfaces;

import com.example.cpp.model.UsedMediaTypesPerMachine;

import java.sql.SQLException;
import java.util.List;

public interface IUsedMediaTypesPerMachineDal {
    List<UsedMediaTypesPerMachine> ListUsedMediaTypesPerMachine() throws SQLException;
    List<UsedMediaTypesPerMachine> ListMediaTypesByMachineID() throws SQLException;
}
