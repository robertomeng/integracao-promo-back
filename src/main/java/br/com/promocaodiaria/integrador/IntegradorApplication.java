package br.com.promocaodiaria.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
@EnableWebMvc
public class IntegradorApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(IntegradorApplication.class, args);
		
	}

}
