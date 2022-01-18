package com.polytech.si5.al.dronedelivery.team.g.truck.interfaces;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FleetAllocation;

import java.util.List;

public interface AllocationProvider {

    List<FleetAllocation> getAllocations();
}
