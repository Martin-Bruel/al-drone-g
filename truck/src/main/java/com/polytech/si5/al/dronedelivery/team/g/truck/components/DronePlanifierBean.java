package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PathFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DronePlanifierBean implements PathFinder {

    Logger logger = LoggerFactory.getLogger(DronePlanifierBean.class);

    @Override
    /**
     * Very simple flight plan
     */
    public FlightPlan getPath(Position truckPos, Position packagePos) {
        logger.info("Determine flight plan");
        List<Position> positions = new ArrayList<>();
        positions.add(truckPos);
        positions.add(packagePos);
        return new FlightPlan(positions);
    }
}
