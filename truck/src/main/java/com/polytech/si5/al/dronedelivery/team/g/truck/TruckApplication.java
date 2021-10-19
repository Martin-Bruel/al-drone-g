package com.polytech.si5.al.dronedelivery.team.g.truck;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DeliveryRepository;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
public class TruckApplication {

	private static final Logger log = LoggerFactory.getLogger(TruckApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TruckApplication.class, args);
	}

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Bean
	public CommandLineRunner createDelivery(DeliveryRepository repository) {
		return (args) -> {
			// Save delivery
			deliveryRepository.save(new Delivery(new Address("Rue Jean Macet", 3, 31300, "Toulouse", new Position(2,2))));


			// Fetch all deliveries
			log.info("Delivery found with findAll():");
			log.info("-------------------------------");
			for (Delivery delivery : deliveryRepository.findAll()) {
				log.info(delivery.toString());
			}
			log.info("");
		};
	}
}
