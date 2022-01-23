package com.example.cpp.ServiceInterfaces;

import com.example.cpp.model.UsedMediaTypesPerMachine;

import java.sql.SQLException;
import java.util.List;

public interface IUsedMediaTypesPerMachineService {
    List<UsedMediaTypesPerMachine> ListUsedMediaTypesPerMachine() throws SQLException;
    List<UsedMediaTypesPerMachine> ListUsedMediaTypesByMachineId(List<String> machineIDs) throws SQLException;

}
