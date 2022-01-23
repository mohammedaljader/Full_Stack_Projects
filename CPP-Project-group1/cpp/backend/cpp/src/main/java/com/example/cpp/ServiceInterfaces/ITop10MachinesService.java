package com.example.cpp.ServiceInterfaces;

import com.example.cpp.model.Top10Machines;

import java.sql.SQLException;
import java.util.List;

public interface ITop10MachinesService {
    List<Top10Machines> ListTop10Machines() throws SQLException;
}
