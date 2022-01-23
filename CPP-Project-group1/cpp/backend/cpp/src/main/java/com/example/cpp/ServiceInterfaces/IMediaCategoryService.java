package com.example.cpp.ServiceInterfaces;

import com.example.cpp.model.MediaCategory;

import java.sql.SQLException;
import java.util.List;

public interface IMediaCategoryService {

    List<MediaCategory> getMediaCategories() throws SQLException;
    List<MediaCategory> getMediaCategoryByTimeframe(String startDate, String endDate) throws  SQLException;
    List<MediaCategory> FilterMediaCatergoriesBasedOnMachineID(List<String> machineIDs, String startDate, String endDate) throws SQLException;
}
