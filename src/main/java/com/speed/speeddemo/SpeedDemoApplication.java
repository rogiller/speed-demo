package com.speed.speeddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpeedDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeedDemoApplication.class, args);
	}

}
