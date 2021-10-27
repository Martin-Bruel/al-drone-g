package com.polytech.si5.al.dronedelivery.team.g.truck.entities;


import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DeliveryDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.enumeration.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    public Delivery(Position position) {
        this.position = position;
        this.deliveryStatus = DeliveryStatus.PENDING;
    }

    public Delivery(DeliveryDto deliveryDto){
        this.position = new Position(deliveryDto.latitude, deliveryDto.longitude);
    }

    public Delivery(){}

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private Position position;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Drone deliveryDrone;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;

    public void setDeliveryDrone(Drone drone){
        this.deliveryDrone = drone;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus){ this.deliveryStatus=deliveryStatus;}

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", position=" + position +
                ", deliveryDrone=" + deliveryDrone +
                '}';
    }
}
