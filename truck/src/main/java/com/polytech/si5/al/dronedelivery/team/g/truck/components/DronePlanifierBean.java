package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PathFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.utils.PositionCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class DronePlanifierBean implements PathFinder {

    Logger logger = LoggerFactory.getLogger(DronePlanifierBean.class);

    @Override
    public FlightPlan getPath(Position truckPos, List<Position> packagePositions) {
        logger.info("Determine flight plan");
        List<Position> positions = new ArrayList<>();
        positions.add(truckPos);
        packagePositions.sort((p1, p2) -> {
            if (PositionCalculator.distance(p1, truckPos) == PositionCalculator.distance(p2, truckPos))
                return 0;
            else return PositionCalculator.distance(p1, truckPos) > PositionCalculator.distance(p2, truckPos) ? 1 : -1;
        });
        positions.addAll(packagePositions);
        return new FlightPlan(positions);
    }
}
