package br.com.promocaodiaria.integrador.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promocaodiaria.integrador.dto.ProdutoPromoDiariaDto;
import br.com.promocaodiaria.integrador.fire.model.EstoqueWrapper;
import br.com.promocaodiaria.integrador.fire.repository.EstoqueRepository;
import br.com.promocaodiaria.integrador.pg.model.ProdutoPromoDiaria;
import br.com.promocaodiaria.integrador.pg.repository.ProdutoPromoDiariaRepository;

@Service
public class ProdutoPromoDiariaService {

	@Autowired
	ProdutoPromoDiariaRepository produtoPromoDiariaRepository;
	
	@Autowired
	EstoqueRepository estoqueRepository;
	
	public void save(List<ProdutoPromoDiariaDto> produtos) {
		produtos.forEach(produtoDto -> {
			EstoqueWrapper estoque = estoqueRepository.findEstoqueById(produtoDto.getIdIdentificador());
			produtoPromoDiariaRepository.save(parserProduto(estoque, produtoDto));	
		});
	}
	
	private ProdutoPromoDiaria parserProduto(EstoqueWrapper estoque, ProdutoPromoDiariaDto dto) {
		
		ProdutoPromoDiaria produtoPromoDiaria = new ProdutoPromoDiaria();
		
		produtoPromoDiaria.setDescricao(estoque.getDescricao());
		produtoPromoDiaria.setDtInicio(dto.getDtInicio());
		produtoPromoDiaria.setDtFim(dto.getDtFim());
		produtoPromoDiaria.setQtAtual(estoque.getQtAtual());
		produtoPromoDiaria.setIdIdentificador(estoque.getIdIdentificador());
		produtoPromoDiaria.setPrcVenda(estoque.getPrcVenda());
		produtoPromoDiaria.setVlCusto(BigDecimal.ZERO);
		produtoPromoDiaria.setVlPromocao(BigDecimal.ZERO);
		produtoPromoDiaria.setCodBarra(estoque.getCodBarra());
		produtoPromoDiaria.setCodNcm(estoque.getCodNcm());
		produtoPromoDiaria.setUniMedida(estoque.getUniMedida());
		
		return produtoPromoDiaria;
		
	}
}
