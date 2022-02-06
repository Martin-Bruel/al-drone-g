package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Step {

    @Setter
    @Getter
    Position position;

    public Step(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Step(" + position.toString() + ')';
    }


    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Step)) return false;
        Step step = (Step) other;
        return this.position.equals(step.position);
    }
}
