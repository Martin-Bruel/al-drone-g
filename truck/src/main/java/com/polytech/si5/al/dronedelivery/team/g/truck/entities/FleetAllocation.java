package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import com.polytech.si5.al.dronedelivery.team.g.truck.views.FleetAllocationView;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class FleetAllocation {

    private final List<Allocation> allocations;

    public FleetAllocation(List<Allocation> allocations) {
        this.allocations = allocations;
    }

    @Override
    public String toString() {
        List<String> allocationsStr = allocations.stream().map(Objects::toString).collect(Collectors.toList());
        return "FleetAllocation{" + "allocations=[" + String.join(",", allocationsStr) + "]}";
    }


    public FleetAllocationView getView() {
        return new FleetAllocationView(this);
    }
}
