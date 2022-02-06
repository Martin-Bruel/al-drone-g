package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

public class DeliveryStep extends Step {

    private final Long deliveryId;

    public DeliveryStep(Position position, Long deliveryId) {
        super(position);
        this.deliveryId = deliveryId;
    }

    public DeliveryStep(Delivery d) {
        this(d.getPosition(), d.getId());
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

}
