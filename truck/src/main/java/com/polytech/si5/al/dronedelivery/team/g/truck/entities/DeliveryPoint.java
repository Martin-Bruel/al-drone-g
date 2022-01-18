package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import java.util.List;

public class DeliveryPoint {
    List<Delivery> deliveries;

    public DeliveryPoint(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }
}
