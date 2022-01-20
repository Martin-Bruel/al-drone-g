package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.Getter;

import java.util.List;

@Getter
public class Fleet {

    private List<Drone> drones;
    private Long leaderDroneId;
    private FlightPlan flightPlan;

    public Fleet(List<Drone> drones, Long leaderDroneId, FlightPlan flightPlan) {
        this.drones = drones;
        this.leaderDroneId = leaderDroneId;
        this.flightPlan = flightPlan;
    }

    @Override
    public String toString() {
        return "Fleet{" +
                "drones=" + drones +
                ", leaderDroneId=" + leaderDroneId +
                ", flightPlan=" + flightPlan +
                '}';
    }
}
