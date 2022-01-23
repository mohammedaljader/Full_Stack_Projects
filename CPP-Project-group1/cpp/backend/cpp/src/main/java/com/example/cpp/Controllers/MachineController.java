package com.example.cpp.Controllers;

import com.example.cpp.ServiceInterfaces.IInkUsageService;
import com.example.cpp.ServiceInterfaces.IMachineIDService;
import com.example.cpp.model.InkUsage;
import com.example.cpp.model.MachineID;
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
public class MachineController {

    private final IMachineIDService machineIDService;


    @Autowired
    public MachineController(IMachineIDService machineIDService) {
        this.machineIDService = machineIDService;

    }

    @GetMapping("/machines")
    public List<MachineID> getInkUsageBasedOnGivenDate() throws SQLException {
        return machineIDService.GetAllMachineID();
    }

}
