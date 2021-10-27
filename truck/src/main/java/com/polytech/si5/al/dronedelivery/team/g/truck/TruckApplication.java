package com.polytech.si5.al.dronedelivery.team.g.truck;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Address;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
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

@SpringBootApplication
@EnableJpaRepositories
public class TruckApplication {

	private static final Logger log = LoggerFactory.getLogger(TruckApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TruckApplication.class, args);
	}


	@Autowired
	private DeliveryRepository deliveryRepository;

	@Bean
	public CommandLineRunner createDelivery() {
		return (args) -> {
			// Save delivery
			deliveryRepository.save(new Delivery(new Address("Rue Jean Macet", 3, 31300, "Toulouse", new Position(2,2))));
			deliveryRepository.save(new Delivery(new Address("Avenue Saint Patrick", 3, 81000, "Albi", new Position(4,2))));
			deliveryRepository.save(new Delivery(new Address("Rue de la paix", 3, 06600, "Antibes", new Position(1,2))));
			deliveryRepository.save(new Delivery(new Address("Chemin Jean Martinet", 3, 75000, "Paris", new Position(5,4))));
			deliveryRepository.save(new Delivery(new Address("Avenue Jeanne darc", 3, 66000, "Perpignan", new Position(3,2))));
			deliveryRepository.save(new Delivery(new Address("Boulevard de Paris", 3, 64000, "Pau", new Position(0,4))));
			deliveryRepository.save(new Delivery(new Address("Route de Saint Jean", 3, 12000, "Rodez", new Position(4,4))));


			// Fetch all deliveries
			log.info("");
			log.info("Delivery found with findAll():");
			log.info("-------------------------------");
			for (Delivery delivery : deliveryRepository.findAll()) {
				log.info("> " + delivery.toString());
			}
			log.info("-------------------------------");
		};
	}
}
