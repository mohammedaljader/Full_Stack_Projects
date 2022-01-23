package com.example.cpp.Services;

import com.example.cpp.DalInterfaces.IMediaCategoryDal;
import com.example.cpp.ServiceInterfaces.IMediaCategoryService;
import com.example.cpp.model.MediaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaCategoryService implements IMediaCategoryService {

    private final IMediaCategoryDal dal;

    @Autowired
    public MediaCategoryService(IMediaCategoryDal dal){
        this.dal = dal;
    }

    @Override
    public List<MediaCategory> getMediaCategories() {
        try {
            return dal.ListMediaCategory();
        }
        catch (Exception ex)
        {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
        return null;


    }

    @Override
    public List<MediaCategory> getMediaCategoryByTimeframe(String startDate, String endDate) throws SQLException {

        return dal.ListMediaCategoryByDate(startDate,endDate);


    }




    @Override
    public List<MediaCategory> FilterMediaCatergoriesBasedOnMachineID(List<String> machineIDs, String startDate, String endDate) throws SQLException {
        List<MediaCategory> returnable = new ArrayList<>();
        List<MediaCategory> mediaCategories = dal.ListMediaCategoryByDate(startDate,endDate);

        for (MediaCategory m : mediaCategories){
            for(String s : machineIDs){
                if(m.getMachine_id().equals(s)){
                    returnable.add(m);
                }
            }
        }
        return returnable;

    }
}
