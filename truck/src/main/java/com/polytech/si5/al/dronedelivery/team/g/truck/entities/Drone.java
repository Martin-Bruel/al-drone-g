package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Drone {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;

    protected Drone() {}

    public Drone(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Drone[id=%d, name='%s'",
                id, name);
    }

}
