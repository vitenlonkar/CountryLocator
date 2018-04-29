package com.assignment.countryfinderrest;

import com.assignment.countryfinderrest.config.CountryFinderException;
import com.assignment.countryfinderrest.utility.TableCreationUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.assignment.*")
public class CountryfinderRestApplication {

	public static void main(String[] args) throws CountryFinderException {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(CountryfinderRestApplication.class, args);
		applicationContext.getBean(TableCreationUtility.class).createTable();
	}
}
