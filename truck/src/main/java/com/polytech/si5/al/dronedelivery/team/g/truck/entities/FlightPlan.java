package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import java.util.List;

public class FlightPlan {

    private List<Position> steps;

    public FlightPlan(List<Position> steps) {
        this.steps = steps;
    }
}
