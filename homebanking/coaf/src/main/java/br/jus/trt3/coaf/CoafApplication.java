package br.jus.trt3.coaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.jus.trt3.coaf.CoafApplication;

@SpringBootApplication
//@ComponentScan({"br.jus.trt3.coaf.repositories","br.jus.trt3.coaf.controllers"})
public class CoafApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoafApplication.class, args);
	}
}