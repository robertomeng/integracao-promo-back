package br.com.promocaodiaria.integrador.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

	private RestTemplate rest = new RestTemplate();
	
	public <T> T post(String url, Object body, Class<T> responseType) {
		
		return rest.postForEntity(url, body, responseType).getBody();
	}
	
	public void put(String url, Object body) {
		rest.put(url, body);
	}
	
}
