package com.example.cpp.Services;

import com.example.cpp.Dal.MachineIDal;
import com.example.cpp.ServiceInterfaces.IMachineIDService;
import com.example.cpp.model.MachineID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MachineIDService implements IMachineIDService {
    private final MachineIDal dal;

    @Autowired
    public MachineIDService(MachineIDal dal){
        this.dal = dal;
    }


    @Override
    public List<MachineID> GetAllMachineID() throws SQLException {
        return dal.GetAllMachineID();
    }
}
