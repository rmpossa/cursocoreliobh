package br.jus.trt3.gateway;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.trt3.gateway.properties.AppProperties;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(AppProperties.class)
public class GatewayApplication {
	
	@Autowired
	private AppProperties appProperties;

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		
		String urlHomeBanking = appProperties.getHostnameHomeBanking();
		String urlCoaf = appProperties.getHostnameCoaf();
		String urlCartaoCredito = appProperties.getHostnameCartaoCredito();
		
		return builder.routes()
	            .route(p -> p
	                .path("/auditorias")
	                .filters(f -> f
	                    	.hystrix(config -> config
	                        .setName("mycmd2")
	                        .setFallbackUri("forward:/fallback")))
	                .uri("http://"+urlCoaf+":8082"))
	            .route(p -> p
	            		.path("/cartaoCreditoes")
	            		.uri("http://"+urlCartaoCredito+":8083"))
	            		
	            .route(p -> p
	            		.path("/cartaoapp/*")
	            		.uri("http://"+urlCartaoCredito+":8083"))
	            .route(p -> p
		                .path("/*")
		                .uri("http://"+urlHomeBanking+":8081"))
	            .build();
	}
	
	 @RequestMapping("/fallback")
	    public Mono<String> fallback() {
	        return Mono.just("fallback");
	    }
	
}
