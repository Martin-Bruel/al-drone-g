package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import lombok.Getter;
import lombok.Setter;

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
}
