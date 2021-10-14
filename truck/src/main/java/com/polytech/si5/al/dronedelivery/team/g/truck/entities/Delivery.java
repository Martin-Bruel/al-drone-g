package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    private Drone deliveryDrone;

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", address=" + address +
                ", deliveryDrone=" + deliveryDrone +
                '}';
    }
}
