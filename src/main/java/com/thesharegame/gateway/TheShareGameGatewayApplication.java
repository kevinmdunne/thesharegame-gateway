package com.thesharegame.gateway;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
public class TheShareGameGatewayApplication {

	@Autowired
	private EurekaClient eurekaClient;

	public static void main(String[] args) {
		SpringApplication.run(TheShareGameGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/portfolio/**")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri(getBaseUri(ServiceName.PORTFOLIO_SERVICE) + "portfolio/"))
				.route(p -> p
						.path("/user/**")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri(getBaseUri(ServiceName.USER_SERVICE) + "user/"))
				.route(p -> p
						.path("/share/**")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri(getBaseUri(ServiceName.SHARE_SERVICE) + "share/"))
				.build();
	}

	private String getBaseUri(ServiceName serviceName){
		log.info("Getting base URI for " + serviceName.serviceName);
		Application application = eurekaClient.getApplication(serviceName.serviceName);
		InstanceInfo service = application.getInstances().get(0);
		return service.getHomePageUrl();
	}

}
