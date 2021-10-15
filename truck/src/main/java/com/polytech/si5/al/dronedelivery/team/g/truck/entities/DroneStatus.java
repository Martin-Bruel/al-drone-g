package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import javax.persistence.Embeddable;

@Embeddable
public enum DroneStatus {
    READY,
    FLYING_TO_DELIVERY,
    FLYING_TO_TRUCK,
    LOST
}
