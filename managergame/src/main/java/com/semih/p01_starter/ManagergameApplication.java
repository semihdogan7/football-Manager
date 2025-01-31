package com.semih.p01_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = {"com.semih"})
@ComponentScan(basePackages = {"com.semih"})
@EnableJpaRepositories(basePackages = {"com.semih"})
public class ManagergameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagergameApplication.class, args);
	}

}
