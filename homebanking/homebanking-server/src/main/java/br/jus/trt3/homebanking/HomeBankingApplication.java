package br.jus.trt3.homebanking;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import br.jus.trt3.homebanking.properties.AppProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan({"br.jus.trt3.homebanking.repositories","br.jus.trt3.homebanking.controllers"})
@EnableConfigurationProperties(AppProperties.class)
public class HomeBankingApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
		
	}

}
