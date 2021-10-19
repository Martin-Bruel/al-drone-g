package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Allocation;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.AllocationProvider;
import com.polytech.si5.al.dronedelivery.team.g.truck.views.AllocationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AllocationController {

    @Autowired
    private AllocationProvider allocationProvider;

    @GetMapping(value="/truck/allocation")
    public List<AllocationView> getAllocations () throws Exception {
        return allocationProvider.getAllocations().stream().map(Allocation::getView).collect(Collectors.toList());
    }
}
