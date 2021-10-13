package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DronePlanifierBeanTest {

    @Autowired
    private DronePlanifierBean dronePlanifierBean;

    @Test
    void getPathTest(){
        Position packagePos = new Position(2,2);
        Position truckPos= new Position(0,0);
        FlightPlan flightPlan = dronePlanifierBean.getPath(truckPos, packagePos);
        assertEquals(flightPlan.getSteps().get(0), truckPos);
        assertEquals(flightPlan.getSteps().get(flightPlan.getSteps().size() - 1), packagePos);
    }
}