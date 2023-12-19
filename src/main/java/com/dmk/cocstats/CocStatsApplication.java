package com.dmk.cocstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CocStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocStatsApplication.class, args);
	}

}
