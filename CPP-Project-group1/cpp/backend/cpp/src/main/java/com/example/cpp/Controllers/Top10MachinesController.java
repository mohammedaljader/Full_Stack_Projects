package com.example.cpp.Controllers;

import com.example.cpp.ServiceInterfaces.IMachineIDService;
import com.example.cpp.ServiceInterfaces.ITop10MachinesService;
import com.example.cpp.model.MachineID;
import com.example.cpp.model.Top10Machines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/engineControl")
public class Top10MachinesController {

    private final ITop10MachinesService top10MachinesService;


    @Autowired
    public Top10MachinesController(ITop10MachinesService top10MachinesService) {
        this.top10MachinesService = top10MachinesService;

    }

    @GetMapping("/top10")
    public List<Top10Machines> getInkUsageBasedOnGivenDate() throws SQLException {
        return top10MachinesService.ListTop10Machines();
    }
}