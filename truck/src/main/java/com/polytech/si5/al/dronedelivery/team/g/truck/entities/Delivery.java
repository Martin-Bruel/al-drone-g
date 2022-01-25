package com.polytech.si5.al.dronedelivery.team.g.truck.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DeliveryDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.enumeration.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Delivery {

    public Delivery(Position position) {
        this.position = position;
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

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private DeliveryPoint deliveryPoint;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnore
    private Drone deliveryDrone;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;

    public void setDeliveryDrone(Drone drone){
        this.deliveryDrone = drone;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus){ this.deliveryStatus=deliveryStatus;}

    @Override
    public int hashCode() {
        return Objects.hash(position,deliveryDrone);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Delivery)) return false;
        Delivery delivery = (Delivery) other;
        return this.position == delivery.position && this.deliveryDrone == delivery.deliveryDrone;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", position=" + position +
                ", deliveryDrone=" + deliveryDrone +
                '}';
    }
}
