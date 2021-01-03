package com.example.ContactAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreLoader {
	
	//Create log for db
	private static final Logger log = LoggerFactory.getLogger(PreLoader.class);
	
	@Bean
	public CommandLineRunner initDB(ContactRepo repo)
	{
		return args -> {
			log.info("Preload " + repo.save(new Contact("Thomas C.", "Pirahna Plant")));
			log.info("Preload " + repo.save(new Contact("Kelly S.", "Pirahna Plant")));
			log.info("Preload " + repo.save(new Contact("Jacob U.", "Alternate Universe")));
			log.info("Preload " + repo.save(new Contact("Steven R.", "Cybersec")));
		};
	}
}
