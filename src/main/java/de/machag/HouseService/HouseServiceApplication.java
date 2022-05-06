package de.machag.HouseService;

import de.machag.HouseService.House.HouseRepository;
import de.machag.HouseService.Tennant.TennantRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses =
		{
				TennantRepository.class,
				HouseRepository.class
		})

public class HouseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseServiceApplication.class, args);
	}

}
