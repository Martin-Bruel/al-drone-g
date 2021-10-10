package com.polytech.si5.al.dronedelivery.team.g.truck.dao;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DroneDAO extends JpaRepository<Drone,Integer> {
    List<Drone> findAll();
    Drone findById(int id);
}
