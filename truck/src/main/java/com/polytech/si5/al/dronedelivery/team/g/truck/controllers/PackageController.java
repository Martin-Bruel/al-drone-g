package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Address;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackageController {

    @Autowired
    DeliveryRepository deliveryRepository;

    Logger logger = LoggerFactory.getLogger(PackageController.class);


    @PostMapping("/init/test/package/{nbPackage}")
    public void start(@PathVariable Integer nbPackage){
        logger.info("init test");
        deliveryRepository.deleteAll();
        for (int i = 0; i < nbPackage; i++){
            deliveryRepository.save(new Delivery(new Address("Rue du test", 22, 22000, "TestCity", new Position(2*nbPackage+1,2*nbPackage+1))));
        }
    }
}
