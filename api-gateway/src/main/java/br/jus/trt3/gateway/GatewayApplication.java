package br.jus.trt3.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		
		return builder.routes()
	            .route(p -> p
	                .path("/auditorias")
	                .filters(f -> f
	                    	.hystrix(config -> config
	                        .setName("mycmd2")
	                        .setFallbackUri("forward:/fallback")))
	                .uri("http://localhost:8082"))
	            .route(p -> p
	            		.path("/cartaoCreditoes")
	            		.uri("http://localhost:8083"))
	            		
	            .route(p -> p
	            		.path("/cartaoapp/*")
	            		.uri("http://localhost:8083"))
	            .route(p -> p
		                .path("/*")
		                .uri("http://localhost:8081"))
	            .build();
	    		  
	}
	
	 @RequestMapping("/fallback")
	    public Mono<String> fallback() {
	        return Mono.just("fallback");
	    }
	
}
