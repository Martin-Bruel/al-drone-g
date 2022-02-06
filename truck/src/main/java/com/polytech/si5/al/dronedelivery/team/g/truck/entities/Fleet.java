package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Fleet {

    private List<Drone> drones;
    private Long leaderDroneId;

    public Fleet(List<Drone> drones, Long leaderDroneId) {
        this.drones = drones;
        this.leaderDroneId = leaderDroneId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(drones,leaderDroneId);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Fleet)) return false;
        Fleet fleet = (Fleet) other;
        return this.drones.equals(fleet.drones) && this.leaderDroneId == fleet.leaderDroneId;
    }

    @Override
    public String toString() {
        return "Fleet{" +
                "drones=" + drones +
                ", leaderDroneId=" + leaderDroneId +
                '}';
    }
}
