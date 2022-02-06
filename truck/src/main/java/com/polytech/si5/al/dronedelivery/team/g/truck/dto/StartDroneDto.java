package com.polytech.si5.al.dronedelivery.team.g.truck.dto;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Fleet;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartDroneDto {
    private FlightPlan flightPlan;
    private Fleet fleet;

    public StartDroneDto(FlightPlan flightPlan, Fleet fleet) {
        this.flightPlan = flightPlan;
        this.fleet = fleet;
    }
}
