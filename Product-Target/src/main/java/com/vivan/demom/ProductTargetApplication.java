package com.vivan.demom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.vivan.demom")
public class ProductTargetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductTargetApplication.class, args);
	}

}
