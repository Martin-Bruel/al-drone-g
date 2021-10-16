package com.polytech.si5.al.dronedelivery.team.g.truck.services;

import com.polytech.si5.al.dronedelivery.team.g.truck.constants.Api;
import com.polytech.si5.al.dronedelivery.team.g.truck.dto.PositionDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeoutException;

@Service
public class DroneService {

    private final RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(DroneService.class);

    public DroneService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RestTemplate buildRestTemplate(int timeout){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(1000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
    public PositionDto getDronePosition(Drone drone) {
        RestTemplate restTemplate= buildRestTemplate(Api.ACCEPTABLE_DRONE_TIMEOUT);
        String port=drone.getConnectionInterface().getPort();
        String host=drone.getConnectionInterface().getHost();
        String url = "http://"+host+":"+port+"/"+ Api.DRONE_API_BASE_URL+"/position";
        ResponseEntity<PositionDto> response= restTemplate.getForEntity(url, PositionDto.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }

    }

    public void launchDrone(FlightPlan flightPlan, Drone drone){
        String port=drone.getConnectionInterface().getPort();
        String host=drone.getConnectionInterface().getHost();
        String url = "http://"+host+":"+port+"/"+Api.DRONE_API_BASE_URL+"/delivery/start";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FlightPlan> request = new HttpEntity<FlightPlan>(flightPlan, headers);
        String response= restTemplate.postForObject(url, request, String.class);
        logger.info(response);
    }
}
