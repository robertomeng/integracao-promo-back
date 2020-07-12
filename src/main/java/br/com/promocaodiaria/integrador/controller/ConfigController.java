package br.com.promocaodiaria.integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.promocaodiaria.integrador.pg.model.Config;
import br.com.promocaodiaria.integrador.pg.repository.ConfigRepository;

@RestController
@RequestMapping("/config")
public class ConfigController {
	
	@Autowired
	ConfigRepository configRepository;

	@GetMapping
	public ResponseEntity<?> getConfig(){
		
		Config config = configRepository.findAll().get(0);
		
		Long minutes = config.getTempoScan() / 1000 / 60;
		
		config.setTempoScan(minutes);
		
		return ResponseEntity.ok(config);
	}
	
	@PostMapping
	public ResponseEntity<?> saveConfig(@RequestBody Config config) {
		
		config.setTempoScan(config.getTempoScan() * 60 * 1000);
		
		Config saveAndFlush = configRepository.saveAndFlush(config);
		
		Long minutes = saveAndFlush.getTempoScan() / 1000 / 60;
		
		config.setTempoScan(minutes);
		
		return ResponseEntity.ok(config);
	}
}
