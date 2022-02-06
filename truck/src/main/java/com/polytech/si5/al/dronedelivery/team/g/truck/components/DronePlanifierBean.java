package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PathFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.utils.PositionCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DronePlanifierBean implements PathFinder {

    Logger logger = LoggerFactory.getLogger(DronePlanifierBean.class);

    @Override
    public FlightPlan getPath(Position truckPos, List<Delivery> deliveries) {
        logger.info("Determine flight plan");
        deliveries = new ArrayList<>(deliveries); //clone
        List<Step> deliverySteps = new ArrayList<>();

        Set<DeliveryPoint> deliveryPoints = deliveries.stream()
                .map(Delivery::getDeliveryPoint)
                .sorted(Comparator.comparingDouble( d -> PositionCalculator.distance(d.getPosition(), truckPos) ))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        for ( DeliveryPoint dp : deliveryPoints) {
            List<Delivery> deliveriesFromDp = deliveries.stream()
                    .filter(d -> d.getDeliveryPoint().equals(dp))
                    .sorted(Comparator.comparingDouble( delivery -> PositionCalculator.distance(delivery.getPosition(), dp.getPosition()) ))
                    .collect(Collectors.toList());

            deliverySteps.add(new Step(dp.getPosition()));
            deliverySteps.addAll(deliveriesFromDp.stream()
                    .map(DeliveryStep::new)
                    .collect(Collectors.toList())
            );

            deliveries.removeAll(deliveriesFromDp);
        }

        Position lastStepPosition = (deliverySteps.size()!=0) ? deliverySteps.get(deliverySteps.size()-1).getPosition() : truckPos;
        deliveries.sort(Comparator.comparingDouble(
                delivery -> PositionCalculator.distance(delivery.getPosition(), lastStepPosition)
        ));
        deliveries.forEach(delivery -> deliverySteps.add(new DeliveryStep(delivery.getPosition(), delivery.getId())));

        return new FlightPlan(deliverySteps, truckPos);
    }
}
