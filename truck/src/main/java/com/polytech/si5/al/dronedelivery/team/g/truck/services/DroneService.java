package com.polytech.si5.al.dronedelivery.team.g.truck.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DroneStateDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DroneService {

    private final RestTemplate restTemplate;

    public DroneService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public DroneStateDto getDroneState(Drone drone) {
        String port=drone.getConnectionInterface().getPort();
        String host=drone.getConnectionInterface().getHost();
        String url = "https://"+host+":"+port+"/drone-{id}";
        ResponseEntity<DroneStateDto> response= this.restTemplate.getForEntity(url, DroneStateDto.class,drone.getId());
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public void launchDrone(FlightPlan flightPlan, Drone drone){
        String port=drone.getConnectionInterface().getPort();
        String host=drone.getConnectionInterface().getHost();
        String url = "http://"+host+":"+port+"/drone-api/delivery/start";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FlightPlan> request = new HttpEntity<FlightPlan>(flightPlan, headers);
        String response= restTemplate.postForObject(url, request, String.class);
    }
}
