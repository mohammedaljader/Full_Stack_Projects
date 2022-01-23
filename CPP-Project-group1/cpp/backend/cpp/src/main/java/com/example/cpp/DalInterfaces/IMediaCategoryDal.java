package com.example.cpp.DalInterfaces;

import com.example.cpp.model.MediaCategory;

import java.sql.SQLException;
import java.util.List;

public interface IMediaCategoryDal {
    List<MediaCategory> ListMediaCategory() throws SQLException;
    List<MediaCategory> ListMediaCategoryByDate(String startDate, String endDate) throws  SQLException;
    List<MediaCategory> GetMediaCategoryByMachineId() throws SQLException;
}
