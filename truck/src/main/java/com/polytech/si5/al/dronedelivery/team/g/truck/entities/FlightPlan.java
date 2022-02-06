package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import java.util.List;

public class FlightPlan {

    private List<Step> steps;

    public FlightPlan(List<Step> steps, Position start) {
        this.steps = steps;
        this.steps.add(0, new Step(start));
        this.steps.add(new Step(start));
    }

    public List<Step> getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return "FlightPlan{" +
                "steps=" + steps.toString() +
                '}';
    }
}
