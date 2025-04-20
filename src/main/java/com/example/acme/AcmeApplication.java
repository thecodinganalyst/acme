package com.example.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.datafaker.Faker;

@SpringBootApplication
public class AcmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmeApplication.class, args);
	}

	@Bean
	public Faker faker(){
		return new Faker();
	}

}
