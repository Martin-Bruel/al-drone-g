package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import java.util.List;

public class FlightPlan {

    private List<Position> steps;
    private List<Position> deliverySteps;

    public FlightPlan(List<Position> steps, List<Position> deliverySteps) {
        this.steps = steps;
        this.deliverySteps = deliverySteps;
    }

    public List<Position> getSteps() {
        return steps;
    }

    public List<Position> getDeliverySteps(){
        return deliverySteps;
    }

    @Override
    public String toString() {
        return "FlightPlan{" +
                "steps=" + steps.toString() +
                ", deliverySteps=" + getDeliverySteps().toString() +
                '}';
    }
}
