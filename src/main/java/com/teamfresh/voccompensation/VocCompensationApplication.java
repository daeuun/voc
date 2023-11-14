package com.teamfresh.voccompensation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VocCompensationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocCompensationApplication.class, args);
	}

}
