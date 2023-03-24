package com.db.LaterCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LaterCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaterCalculatorApplication.class, args);
	}

}
