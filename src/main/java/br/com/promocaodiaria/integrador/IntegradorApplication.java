package br.com.promocaodiaria.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
@EnableWebMvc
@ComponentScan
public class IntegradorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(IntegradorApplication.class, args);
		
	}

}
