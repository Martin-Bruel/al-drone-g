package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
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
        List<Position> packagePositions = new ArrayList<>();
        packagePositions.add(new Position(2,2));
        Position truckPos= new Position(0,0);
        FlightPlan flightPlan = pathFinder.getPath(truckPos, packagePositions);
        System.out.println(flightPlan);
        assertEquals(flightPlan.getSteps().get(0), truckPos);
        assertEquals(flightPlan.getSteps().get(flightPlan.getSteps().size() - 1), packagePositions.get(0));
    }

    @Test
    void getPathSeveralPackageTest(){
        List<Position> packagePositions = new ArrayList<>();

        Position pos1 = new Position(2,2);
        Position pos2 = new Position(3,2);
        Position pos3 = new Position(4,2);

        packagePositions.add(pos2);
        packagePositions.add(pos3);
        packagePositions.add(pos1);

        Position truckPos= new Position(0,0);
        FlightPlan flightPlan = pathFinder.getPath(truckPos, packagePositions);
        assertEquals(flightPlan.getSteps().get(0), truckPos);
        assertEquals(flightPlan.getSteps().get(flightPlan.getSteps().size() - 1), pos3);
        assertEquals(flightPlan.getSteps().get(flightPlan.getSteps().size() - 2), pos2);
        assertEquals(flightPlan.getSteps().get(flightPlan.getSteps().size() - 3), pos1);
    }
}
