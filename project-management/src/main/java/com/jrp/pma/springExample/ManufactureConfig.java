package com.jrp.pma.springExample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManufactureConfig {

	// A Bean can be defined in a Configuration class.
	@Bean
	public Car newCar() {
		Engine engine = new Engine();
		Doors doors = new Doors();
		Tires tires = new Tires();
		return new Car(engine, doors, tires);
	}

}
