package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DeliveryDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DeliveryStateDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageModifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageRegistration;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackageController {

    @Autowired
    PackageRegistration packageRegistration;

    @PostMapping("/package/add")
    public void addPackages(@RequestBody DeliveryDto deliveryDto){
        packageRegistration.registerDelivery(new Delivery(deliveryDto));
    }
}
