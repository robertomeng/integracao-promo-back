package br.com.promocaodiaria.integrador.schedules;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Agendador {
	
	@Scheduled(fixedDelayString  = "${miliseconds.scan.produtos}")
	public void atualizarProdutos() {
		System.out.println("teste ########################S");
	}
}
