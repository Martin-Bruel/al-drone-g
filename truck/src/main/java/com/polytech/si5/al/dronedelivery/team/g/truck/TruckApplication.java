package com.polytech.si5.al.dronedelivery.team.g.truck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TruckApplication {

	private static final Logger log = LoggerFactory.getLogger(TruckApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TruckApplication.class, args);
	}

}
