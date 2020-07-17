package br.com.promocaodiaria.integrador.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.promocaodiaria.integrador.dto.ProdutoPromoDiariaDto;
import br.com.promocaodiaria.integrador.fire.model.EstoqueWrapper;
import br.com.promocaodiaria.integrador.fire.repository.EstoqueRepository;
import br.com.promocaodiaria.integrador.pg.repository.ProdutoPromoDiariaRepository;
import br.com.promocaodiaria.integrador.service.ProdutoPromoDiariaService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	EstoqueRepository estoqueRepository;

	@Autowired
	BuildProperties buildProperties;
	
	@Autowired
	ProdutoPromoDiariaService produtoPromoDiariaService;
	
	@Autowired
	ProdutoPromoDiariaRepository produtoPromoDiariaRepository;
	
	@GetMapping
	public List<EstoqueWrapper> findEstoqueByName(@RequestParam String query) {
		
		return estoqueRepository.findEstoqueByDescricao(query);
	}
	
	@GetMapping("promocoes")
	public ResponseEntity<?> produtosPromocao(Integer page) {
		
		PageRequest of = PageRequest.of(page, 10, Sort.by(Direction.ASC, "descricao"));
		
		return ResponseEntity.ok(produtoPromoDiariaRepository.findAll(of));
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody List<ProdutoPromoDiariaDto> produtos) {
		
		produtoPromoDiariaService.save(produtos);
		
		return ResponseEntity.noContent().build();
	}

	@GetMapping("version")
	public Map<String, String> version() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("versao", buildProperties.get("versao.projeto"));
		
		return map;
	}
}
