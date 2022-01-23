package com.example.cpp.Controllers;

import com.example.cpp.ServiceInterfaces.IMediaCategoryService;
import com.example.cpp.ServiceInterfaces.ISqmPerPrintModeService;
import com.example.cpp.model.MediaCategory;
import com.example.cpp.model.SqmPerPrintMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/engineControl/sqm")
public class SqmPerPrintModeController {

    private final ISqmPerPrintModeService sqmPerPrintModeService;

    @Autowired
    public SqmPerPrintModeController(ISqmPerPrintModeService sqmPerPrintModeService)
    {
        this.sqmPerPrintModeService = sqmPerPrintModeService;

    }

    @PostMapping("/test1")
    public List<SqmPerPrintMode> getSqmPerPrintModeByTimeframe(@RequestBody SqmPerPrintModeDTO sqmPerPrintModeDTO) throws SQLException {
        return sqmPerPrintModeService.ListAllSqmPerPrintModeByTimeFrame(sqmPerPrintModeDTO.getStartDate(),sqmPerPrintModeDTO.getEndDate());
    }
}

class SqmPerPrintModeDTO {
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}

