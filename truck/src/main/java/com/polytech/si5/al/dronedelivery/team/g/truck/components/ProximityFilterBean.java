package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.constants.Api;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.DeliveryPoint;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageSelector;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PositionProvider;
import com.polytech.si5.al.dronedelivery.team.g.truck.utils.PositionCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProximityFilterBean implements PackageSelector {

    Logger logger = LoggerFactory.getLogger(ProximityFilterBean.class);

    @Autowired
    PackageFinder packageFinder;

    @Autowired
    PositionProvider positionProvider;

    @Override
    public List<Delivery> getDeliverySelected() {
        logger.info("Filter deliveries");

        Position truckPosition = positionProvider.getTruckPosition();
        List<Delivery> deliveries = packageFinder.getDeliverablePackages();
        deliveries.removeIf(d -> PositionCalculator.distance(truckPosition, d.getPosition()) > Api.DRONE_RANGE);
        return deliveries;
    }

    @Override
    public List<DeliveryPoint> getReachableDeliveryPoints() {
        // TODO: 16/01/2022 Group deliveries into DeliveryPoints
        List<Delivery> reachableDeliveries = this.getDeliverySelected();
        DeliveryPoint dp = new DeliveryPoint(reachableDeliveries);
        reachableDeliveries.forEach(delivery -> delivery.setDeliveryPoint(dp));
        return new ArrayList<>(Arrays.asList(dp));
    }
}
