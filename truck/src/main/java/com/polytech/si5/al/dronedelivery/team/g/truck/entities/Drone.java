package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Drone {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;


    @OneToMany( targetEntity=Delivery.class, mappedBy="deliveryDrone" )
    private List<Delivery> commands = new ArrayList<>();

    @Override
    public String toString() {
        return String.format(
                "Drone-%d", id);
    }

}
