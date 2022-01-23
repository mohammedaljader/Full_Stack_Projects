package com.example.cpp.Services;

import com.example.cpp.DalInterfaces.ISqmPerPrintModeDal;
import com.example.cpp.DalInterfaces.ITop10MachinesDal;
import com.example.cpp.ServiceInterfaces.ISqmPerPrintModeService;
import com.example.cpp.ServiceInterfaces.ITop10MachinesService;
import com.example.cpp.model.InkUsage;
import com.example.cpp.model.Top10Machines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Top10MachinesService  implements ITop10MachinesService {


    private final ITop10MachinesDal dal;

    @Autowired
    public Top10MachinesService(ITop10MachinesDal dal) {
        this.dal = dal;
    }

    @Override
    public List<Top10Machines> ListTop10Machines() throws SQLException {
         List<Top10Machines> top10Machines = new ArrayList<>();


        for(Top10Machines top10 :dal.ListTop10Machines()) {
            if (top10.getSum() != 0) {
                top10.setSum(top10.getSum() / 100);
            }
            top10Machines.add(top10);
        }


        return top10Machines;
    }
}
