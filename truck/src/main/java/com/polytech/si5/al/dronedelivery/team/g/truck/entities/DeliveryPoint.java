package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class DeliveryPoint {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
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

    public Position getPosition() {
        return position;
    }
}
