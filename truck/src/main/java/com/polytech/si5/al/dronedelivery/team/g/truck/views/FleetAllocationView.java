package com.polytech.si5.al.dronedelivery.team.g.truck.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FleetAllocation;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FleetAllocationView {

    @JsonProperty("allocations")
    private List<AllocationView> allocationViews;

    public FleetAllocationView(FleetAllocation a) {
        this.allocationViews = a.getAllocations().stream()
                .map(AllocationView::new)
                .collect(Collectors.toList());
    }

}
