package br.jus.trt3.cartao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.jus.trt3.cartao.properties.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class CartaoCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartaoCreditoApplication.class, args);
	}
	
}
