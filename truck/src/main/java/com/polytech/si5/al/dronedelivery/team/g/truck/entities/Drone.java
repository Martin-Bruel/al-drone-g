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
    private Long id;
    private String name;
    public ConnectionInterface connectionInterface;

    public Drone(String name, ConnectionInterface connectionInterface) {
        this.name=name;
        this.connectionInterface=connectionInterface;
    }
    public Drone(ConnectionInterface connectionInterface){
        this.connectionInterface=connectionInterface;
    }
    public Drone(String name){
        this.name=name;
    }

    @OneToMany( targetEntity=Delivery.class, mappedBy="deliveryDrone" )
    private List<Delivery> deliveries = new ArrayList<>();

    public Drone() {

    }


    @Override
    public String toString() {
        return String.format(
                "Drone[id = %d, name = %s]", id,name);
    }

}
