package com.thesharegame.gateway;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TheShareGameGatewayApplication {

	@Value("${gateway.host}")
	private String host;

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
						.uri("http://" + host + ":8082/share/"))
				.build();
	}

	private String getBaseUri(ServiceName serviceName){
		Application application = eurekaClient.getApplication(serviceName.serviceName);
		InstanceInfo service = application.getInstances().get(0);
		return service.getHomePageUrl();
	}

}
