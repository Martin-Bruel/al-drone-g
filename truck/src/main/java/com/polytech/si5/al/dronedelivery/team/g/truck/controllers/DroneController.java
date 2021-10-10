package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.dao.DroneDAO;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DroneController {

    @Autowired
    private DroneDAO droneDao;

    @RequestMapping(value="/drones", method= RequestMethod.GET)
    public List<Drone> getAllDrones() {
        return droneDao.findAll();
    }

    @GetMapping(value="/drones/{id}")
    public Drone getDrone(@PathVariable int id) {
        return droneDao.findById(id);
    }
}
