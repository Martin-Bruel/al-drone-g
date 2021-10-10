package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    private Address address;

    @ManyToOne
    private Drone deliveryDrone;


}
