package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication springApplication =
				new SpringApplicationBuilder()
						.sources(DemoApplication.class)
						//WebFlux and Spring MVC cannot run togheter so enabling only WebFlux
						.web(WebApplicationType.REACTIVE)
						.build();
		springApplication.run(args);
	}
}
