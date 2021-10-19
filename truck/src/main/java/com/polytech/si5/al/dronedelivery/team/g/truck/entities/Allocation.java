package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import com.polytech.si5.al.dronedelivery.team.g.truck.views.AllocationView;
import lombok.Getter;

@Getter
public class Allocation {

    private final Drone drone;
    private final Delivery delivery;

    public Allocation(Drone drone, Delivery delivery) {
        this.drone = drone;
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Position{" +
                "drone=" + drone.getId() +
                ", delivery=" + delivery.getId() +
                '}';
    }

    public AllocationView getView() {
        return new AllocationView(this);
    }
}
