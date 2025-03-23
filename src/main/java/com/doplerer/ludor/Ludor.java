package com.doplerer.ludor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Ludor {

	public static void main(String[] args) {
		SpringApplication.run(Ludor.class, args);
	}

}
