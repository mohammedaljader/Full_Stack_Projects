package com.example.cpp.Services;

import com.example.cpp.DalInterfaces.IUsedMediaTypesPerMachineDal;
import com.example.cpp.ServiceInterfaces.IUsedMediaTypesPerMachineService;
import com.example.cpp.model.UsedMediaTypesPerMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsedMediaTypesPerMachineService implements IUsedMediaTypesPerMachineService {

    private final IUsedMediaTypesPerMachineDal dal;

    @Autowired
    public UsedMediaTypesPerMachineService(IUsedMediaTypesPerMachineDal dal)
    {
        this.dal = dal;
    }

    @Override
    public List<UsedMediaTypesPerMachine> ListUsedMediaTypesPerMachine() throws SQLException {
        return dal.ListUsedMediaTypesPerMachine();
    }

    @Override
    public List<UsedMediaTypesPerMachine> ListUsedMediaTypesByMachineId(List<String> machineIDs) throws SQLException {
        List<UsedMediaTypesPerMachine> returnable = new ArrayList<>();
        List<UsedMediaTypesPerMachine> usedMediaTypes = dal.ListMediaTypesByMachineID();
        List<UsedMediaTypesPerMachine> finalList = new ArrayList<>();

        for (UsedMediaTypesPerMachine u : usedMediaTypes){
            for(String m : machineIDs){
                if(u.getMachineID().equals(m)){
                    returnable.add(u);
                }
            }
        }

        for (UsedMediaTypesPerMachine usedMediaTypesPerMachine : returnable)
        {
            if(usedMediaTypesPerMachine.getArea() != 0)
            {
                usedMediaTypesPerMachine.setArea(usedMediaTypesPerMachine.getArea() / 100);
            }
            finalList.add(usedMediaTypesPerMachine);
        }
        return finalList;
    }
}
