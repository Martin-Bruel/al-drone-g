package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PathFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DronePlanifierBeanTest {

    @Autowired
    private PathFinder pathFinder;

    @Test
    void getPathTest(){
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(new Delivery(new Position(2,2)));
        deliveries.forEach(d -> d.setDeliveryPoint(new DeliveryPoint(deliveries)));

        Position truckPos= new Position(0,0);
        FlightPlan flightPlan = pathFinder.getPath(truckPos, deliveries);
        System.out.println(flightPlan);
        assertEquals(deliveries.get(0).getId(), ((DeliveryStep) flightPlan.getSteps().get(2)).getDeliveryId());

    }

    @Test
    void getPathSeveralPackageTest(){
        List<Delivery> deliveries = new ArrayList<>();

        Delivery d1 = new Delivery(new Position(2,2));
        Delivery d2 = new Delivery(new Position(2,3));
        Delivery d3 = new Delivery(new Position(2,4));

        deliveries.add(d2);
        deliveries.add(d3);
        deliveries.add(d1);

        deliveries.forEach(d -> d.setDeliveryPoint(new DeliveryPoint(deliveries)));

        Position truckPos= new Position(0,0);
        FlightPlan flightPlan = pathFinder.getPath(truckPos, deliveries);
        assertEquals(d1.getId(), ((DeliveryStep) flightPlan.getSteps().get(2)).getDeliveryId());
        assertEquals(d2.getId(), ((DeliveryStep) flightPlan.getSteps().get(3)).getDeliveryId());
        assertEquals(d3.getId(), ((DeliveryStep) flightPlan.getSteps().get(4)).getDeliveryId());
    }

    @Test
    void getPathForDeliveryPointsTest(){
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(new Delivery(new Position(2,2)));
        deliveries.add(new Delivery(new Position(4,4)));
        DeliveryPoint dp = new DeliveryPoint(deliveries);

        deliveries.forEach(d -> d.setDeliveryPoint(dp));

        Position truckPos= new Position(0,0);
        FlightPlan flightPlan = pathFinder.getPath(truckPos, deliveries);

        assertEquals(new Step(new Position(3,3)), flightPlan.getSteps().get(1));
    }

    @Test
    void getPathForPartialDeliveryPointsTest(){
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(new Delivery(new Position(2,2)));
        deliveries.add(new Delivery(new Position(4,4)));
        deliveries.add(new Delivery(new Position(6,6)));

        DeliveryPoint dp = new DeliveryPoint(deliveries);

        deliveries.forEach(d -> d.setDeliveryPoint(dp));

        deliveries.remove(2);

        Position truckPos= new Position(0,0);
        FlightPlan flightPlan = pathFinder.getPath(truckPos, deliveries);
        assertEquals(new Step(new Position(4,4)), flightPlan.getSteps().get(1));
    }
}
