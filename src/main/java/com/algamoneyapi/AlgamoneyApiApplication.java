package com.algamoneyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.algamoneyapi.config.property.AgamoneyApiProperty;

@SpringBootApplication
// chamad a da casse que configura se fai ser tru ou false 
@EnableConfigurationProperties(AgamoneyApiProperty.class)
public class AlgamoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyApiApplication.class, args);
	}

	/*
	 * // a qui é o crosass a nivel global da aplicação
	 * 
	 * @Bean  ainda nao ta tao bom por causa do sprin oauth
	 * 
	 * @SuppressWarnings("deprecation") public WebMvcConfigurer corsConfigurer() {
	 * return new WebMvcConfigurerAdapter() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/*").allowedOrigins("http://localhost:8080"); } }; }
	 */
	
}












