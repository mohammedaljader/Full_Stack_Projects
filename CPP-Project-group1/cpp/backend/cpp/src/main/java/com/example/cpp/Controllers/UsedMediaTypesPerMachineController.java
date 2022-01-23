package com.example.cpp.Controllers;

import com.example.cpp.ServiceInterfaces.ITop10MachinesService;
import com.example.cpp.ServiceInterfaces.IUsedMediaTypesPerMachineService;
import com.example.cpp.model.InkUsage;
import com.example.cpp.model.Top10Machines;
import com.example.cpp.model.UsedMediaTypesPerMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/engineControl/usedMediaTypes")
public class UsedMediaTypesPerMachineController {

    private final IUsedMediaTypesPerMachineService service;

    @Autowired
    public UsedMediaTypesPerMachineController(IUsedMediaTypesPerMachineService service) {
        this.service = service;

    }

    @GetMapping("/test2")
    public List<UsedMediaTypesPerMachine> getMediaTypesPerMachine() throws SQLException {


        return service.ListUsedMediaTypesPerMachine();
    }
    @PostMapping("/usedMediaByMachineId")
    public List<UsedMediaTypesPerMachine> getMediaTypesPerMachine(@RequestBody List<String> machineIDs) throws SQLException {
        return service.ListUsedMediaTypesByMachineId(machineIDs);

    }

}
