package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.constants.Api;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageSelector;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PositionProvider;
import com.polytech.si5.al.dronedelivery.team.g.truck.utils.PositionCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProximityFilterBean implements PackageSelector {

    @Autowired
    PackageFinder packageFinder;

    @Autowired
    PositionProvider positionProvider;

    @Override
    public List<Delivery> getDeliverySelected() {

        Position truckPosition = positionProvider.getTruckPosition();
        List<Delivery> deliveries = packageFinder.getDeliverablePackages();
        deliveries.removeIf(d -> PositionCalculator.distance(truckPosition, d.getAddress().getPosition()) > Api.DRONE_RANGE);
        return deliveries;
    }
}
