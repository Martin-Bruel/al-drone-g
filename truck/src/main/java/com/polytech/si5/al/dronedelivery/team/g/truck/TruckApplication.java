package com.polytech.si5.al.dronedelivery.team.g.truck;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.ConnectionInterface;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DroneRepository;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TruckApplication {

	private static final Logger log = LoggerFactory.getLogger(TruckApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TruckApplication.class, args);
	}

	@Autowired
	private DroneRepository droneRepository;

	@Bean
	public CommandLineRunner demo(DroneRepository repository) {
		return (args) -> {
			// save a few drones
			droneRepository.save(new Drone("Alpha",new ConnectionInterface("localhost","8084")));
			droneRepository.save(new Drone("Tango"));
			droneRepository.save(new Drone("Charly"));


			// fetch all drones
			log.info("Drones found with findAll():");
			log.info("-------------------------------");
			for (Drone drone : droneRepository.findAll()) {
				log.info(drone.toString());
			}
			log.info("");
		};
	}
}
