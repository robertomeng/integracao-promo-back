package br.com.promocaodiaria.integrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.promocaodiaria.integrador.pg.model.Config;
import br.com.promocaodiaria.integrador.pg.repository.ConfigRepository;

@RestController
@RequestMapping("/setores")
public class SetorController {

	@Autowired
	ConfigRepository configRepository;
	
	@GetMapping
	public ResponseEntity<List> getSetores() {
		
		Config config = configRepository.findAll().get(0);
		String url = config.getUrlIntegracao().concat("/getSetoresEmpresa/").concat(config.getToken());
		
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.getForEntity(url, List.class);
	}
}
