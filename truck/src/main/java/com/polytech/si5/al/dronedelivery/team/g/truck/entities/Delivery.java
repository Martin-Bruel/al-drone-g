package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import com.polytech.si5.al.dronedelivery.team.g.truck.enumeration.DeliveryStatus;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    public Delivery(Address address) {
        this.id = id;
        this.address = address;
        this.deliveryDrone = deliveryDrone;
    }

    public Delivery(){}

    @Id
    @GeneratedValue
    private Long id;

    private Address address;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Drone deliveryDrone;

    private DeliveryStatus deliveryStatus=DeliveryStatus.DOCKED;

    public void setDeliveryDrone(Drone drone){
        this.deliveryDrone = drone;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus){ this.deliveryStatus=deliveryStatus;}

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", address=" + address +
                ", deliveryDrone=" + deliveryDrone +
                '}';
    }
}
