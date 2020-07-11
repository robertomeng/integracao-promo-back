package br.com.promocaodiaria.integrador.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.promocaodiaria.integrador.fire.model.EstoqueWrapper;
import br.com.promocaodiaria.integrador.fire.repository.EstoqueRepository;
import br.com.promocaodiaria.integrador.pg.model.ProdutoPromoDiaria;
import br.com.promocaodiaria.integrador.pg.repository.ProdutoPromoDiariaRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	EstoqueRepository estoqueRepository;

	@Autowired
	BuildProperties buildProperties;
	
	@Autowired
	ProdutoPromoDiariaRepository produtoPromoDiariaRepository;
	
	@GetMapping
	public List<EstoqueWrapper> findEstoqueByName(@RequestParam String query, @RequestParam Integer page) {
		PageRequest of = PageRequest.of(page, 20, Sort.by(Direction.ASC, "descricao"));
		return estoqueRepository.findEstoqueByName(query);
	}
	
	@GetMapping(value="promocoes")
	public Page<ProdutoPromoDiaria> produtosPromocao(Integer page) {
		PageRequest of = PageRequest.of(page, 20, Sort.by(Direction.ASC, "descricao"));
		return produtoPromoDiariaRepository.findAll(of);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> salvar(@RequestBody List<ProdutoPromoDiaria> produtos) {

		return ResponseEntity.noContent().build();
	}

	@GetMapping("version")
	public Map version() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("versao", buildProperties.get("versao.projeto"));

		return map;
	}

}
