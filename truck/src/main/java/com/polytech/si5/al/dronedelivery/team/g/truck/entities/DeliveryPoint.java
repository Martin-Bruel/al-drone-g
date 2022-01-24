package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import java.util.List;

public class DeliveryPoint {
    List<Delivery> deliveries;

    @Column(name="position")
    private Position position;

    public DeliveryPoint() {
        this(new ArrayList<>());
    }

    public DeliveryPoint(List<Delivery> deliveries) {
        this.deliveries = deliveries;
        this.position = computeCenter();
    }

    private Position computeCenter() {
        List<Position> positions = deliveries.stream().map(Delivery::getPosition).collect(Collectors.toList());
        double latitude = 0.0;
        double longitude = 0.0;
        
        if (positions.size() > 0) { // TODO: 24/01/2022
            for (Position position : positions) {
                latitude += position.getLatitude();
                longitude += position.getLongitude();
            }
            latitude /= (double) positions.size();
            longitude /= (double) positions.size();
        }
        return new Position(latitude,longitude);
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }
}
