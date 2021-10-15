package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Allocation;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.AllocationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AllocationController {

    @Autowired
    private AllocationProvider allocationProvider;

    @GetMapping(value="/truck/allocation")
    public List<Allocation> getAllocations () throws Exception {
        return allocationProvider.getAllocations();
    }
}
