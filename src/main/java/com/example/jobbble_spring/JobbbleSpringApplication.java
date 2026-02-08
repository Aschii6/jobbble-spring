package com.example.jobbble_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.jobbble_spring.repositories")
@ComponentScan(basePackages = "com.example.jobbble_spring")
@EntityScan(basePackages = "com.example.jobbble_spring.entities")
public class JobbbleSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobbbleSpringApplication.class, args);
	}

}
