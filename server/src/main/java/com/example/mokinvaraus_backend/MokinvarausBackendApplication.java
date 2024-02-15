package com.example.mokinvaraus_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.lang.NonNull;


@SpringBootApplication
public class MokinvarausBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(MokinvarausBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		//TODO: tälle parempi ratkaisu
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/api/**");
			}
		};
	}

}
