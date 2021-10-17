package com.polytech.si5.al.dronedelivery.team.g.truck.views;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Allocation;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class AllocationView {

    private Long droneId;
    private List<Long> deliveryIds;

    public AllocationView(Allocation a) {
        this.droneId = a.getDrone().getId();
        this.deliveryIds = Collections.singletonList(a.getDelivery().getId());
    }

}
