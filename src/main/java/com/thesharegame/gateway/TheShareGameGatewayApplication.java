package com.thesharegame.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TheShareGameGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheShareGameGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/portfolio/**")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://localhost:8083/portfolio/"))
				.route(p -> p
						.path("/portfolio/**")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://localhost:8083/portfolio/"))
				.build();
	}

}
