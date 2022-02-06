package com.polytech.si5.al.dronedelivery.team.g.truck.services;

import com.polytech.si5.al.dronedelivery.team.g.truck.constants.Api;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Fleet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {

    private final RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(DroneService.class);

    public MapService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void addDrone(Drone drone){

        String port=Api.MAP_API_PORT;
        String host=Api.MAP_API_HOST;
        String url = "http://"+host + ":" + port+"/"+ Api.MAP_API_BASE_URL+"/add/drone";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Drone> request = new HttpEntity<Drone>(drone, headers);

        try {
            String response= restTemplate.postForObject(url, request, String.class);
            logger.debug(response);
        }
        catch (Exception e){
            logger.info("Cannot post drone to map");
        }

    }
}
