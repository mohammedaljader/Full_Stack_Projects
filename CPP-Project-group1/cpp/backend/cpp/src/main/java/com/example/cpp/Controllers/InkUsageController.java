package com.example.cpp.Controllers;

import com.example.cpp.ServiceInterfaces.IInkUsageService;
import com.example.cpp.model.InkUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/engineControl")
public class InkUsageController {

    private final IInkUsageService inkUsageService;


    @Autowired
    public InkUsageController(IInkUsageService inkUsageService1)
    {
        this.inkUsageService = inkUsageService1;

    }

    @GetMapping("/test")
    public List<InkUsage> getInkUsageBasedOnGivenDate() throws SQLException {
        return inkUsageService.getInkUsageByTime();
    }
    @PostMapping("/test2")
    public List<InkUsage> getInkUsage(@RequestBody InkUsageDTO inkUsage) throws SQLException {

        return inkUsageService.getInkUsageByTimeFrame(inkUsage.getStartDate(),inkUsage.getEndDate());
    }
    @PostMapping("/test3")
    public List<InkUsage> filterInkUsageBasedOnMachineIDs(@RequestBody InkUsageDTO inkUsageDTO) throws SQLException {
        return inkUsageService.FilterInkUsageBasedOnMachineIDs(inkUsageDTO.getMachines(),inkUsageDTO.getStartDate(),inkUsageDTO.getEndDate());
    }

}

class InkUsageDTO{
    private List<String> machines;
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<String> getMachines() {
        return machines;
    }
}
