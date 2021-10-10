package com.polytech.si5.al.dronedelivery.team.g.truck;

import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DroneRepository;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@Bean
	public CommandLineRunner demo(DroneRepository repository) {
		return (args) -> {
			// save a few drones
			repository.save(new Drone("Alpha"));
			repository.save(new Drone("Tango"));
			repository.save(new Drone("Charly"));


			// fetch all drones
			log.info("Drones found with findAll():");
			log.info("-------------------------------");
			for (Drone drone : repository.findAll()) {
				log.info(drone.toString());
			}
			log.info("");
		};
	}
}
