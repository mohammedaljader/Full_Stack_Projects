package com.example.cpp.Services;

import com.example.cpp.DalInterfaces.IInkUsageDal;
import com.example.cpp.Factories.InkUsageFactory;
import com.example.cpp.ServiceInterfaces.IInkUsageService;
import com.example.cpp.model.InkUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InkUsageService implements IInkUsageService {

    private final IInkUsageDal dal;
    private InkUsageFactory factory;

    @Autowired
    public InkUsageService(IInkUsageDal inkUsageService){
        this.dal = inkUsageService;
    }

    @Override
    public List<InkUsage> getInkUsageByTime() {
        try {
            return dal.ListOfInkUsage();
        }
        catch (Exception ex)
        {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<InkUsage> getInkUsageByTimeFrame(String startDate, String endDate) throws SQLException {

          return dal.ListInkUsageByTimeFrame(startDate,endDate);


    }



    @Override
    public List<InkUsage> FilterInkUsageBasedOnMachineIDs(List<String> machineIds, String startDate, String endDate) throws SQLException {
        List<InkUsage> usages = dal.ListInkUsageByTimeFrame(startDate,endDate);
        List<InkUsage> returnable = new ArrayList<>();
        List<InkUsage> finalList = new ArrayList<>();
        for (InkUsage usage : usages
             ) {
            for (String s : machineIds
                 ) {
                if(usage.getMachine_id().equals(s)){
                    returnable.add(usage);
                }
            }
        }


        return returnable;
    }
}
