package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PathFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DronePlanifierBean implements PathFinder {

    @Override
    /**
     * Very simple flight plan
     */
    public FlightPlan getPath(Position truckPos, Position packagePos) {
        log.info("DronePlanifier - Determine flight plan from truc to {}", packagePos);
        return new FlightPlan(List.of(truckPos, packagePos));
    }
}
