package org.iskon.kirtan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"org.iskon.models"})
@EnableJpaRepositories(basePackages = {"org.iskon.repositories"})
public class KirtanApplication {

	public static void main(String[] args) {
		SpringApplication.run(KirtanApplication.class, args);
	}

}
