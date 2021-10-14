package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PositionProvider;
import org.springframework.stereotype.Component;

@Component
public class TruckLocatorBean implements PositionProvider {
    @Override
    public Position getTruckPosition() {
        System.out.println("TruckLocation - get truck location");
        return new Position(0,0);
    }
}
