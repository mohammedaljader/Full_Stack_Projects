package com.example.cpp.Services;

import com.example.cpp.DalInterfaces.IMediaCategoryDal;
import com.example.cpp.DalInterfaces.ISqmPerPrintModeDal;
import com.example.cpp.ServiceInterfaces.ISqmPerPrintModeService;
import com.example.cpp.model.MediaCategory;
import com.example.cpp.model.SqmPerPrintMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SqmPerPrintModeService implements ISqmPerPrintModeService {

    private final ISqmPerPrintModeDal dal;

    @Autowired
    public SqmPerPrintModeService(ISqmPerPrintModeDal dal){
        this.dal = dal;
    }


    @Override
    public List<SqmPerPrintMode> ListAllSqmPerPrintModeByTimeFrame(String startDate, String endDate) throws SQLException {

        return dal.ListAllSqmPerPrintModeByTimeFrame(startDate,endDate);

    }
}
