package br.com.promocaodiaria.integrador.service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	
	@Autowired
	IntegracaoPromocaoDiariaService integracaoPromocaoDiariaService;
	
	public void save(List<ProdutoPromoDiariaDto> produtos) {
		
		produtos.forEach(produtoDto -> {
			
			EstoqueWrapper estoque = estoqueRepository.findEstoqueById(produtoDto.getIdIdentificador());
			
			ProdutoPromoDiaria produto = new ProdutoPromoDiaria();
			
			parserProduto(produto, estoque, produtoDto.getDtInicio(), produtoDto.getDtFim());
			
			Response response = integracaoPromocaoDiariaService.adicionarProduto(produto);
			
			handlerResponse(response, produto);
			
			produtoPromoDiariaRepository.save(produto);
		});
	}
	
	public ProdutoPromoDiaria update(EstoqueWrapper estoque, ProdutoPromoDiaria produto) {
		
		parserProduto(produto, estoque, produto.getDtInicio(), produto.getDtFim());

		try {
		
			Response response = integracaoPromocaoDiariaService.editarProduto(produto);
		
			handlerResponse(response, produto);
		
		} catch(Exception e) {
			
			produto.setSync(false);
			produto.setLog(e.getMessage());
			
		}
		
		return produtoPromoDiariaRepository.saveAndFlush(produto);	
	}
	
	private void handlerResponse(Response response, ProdutoPromoDiaria produto) {
		
		if("OK".equalsIgnoreCase(response.getStatus())) {
			
			produto.setSync(true);
			produto.setLog(null);
		
		} else {
			produto.setSync(false);
			produto.setLog(response.getMensagem());
		}
	}
	
	private void parserProduto(ProdutoPromoDiaria produtoPromoDiaria, EstoqueWrapper estoque, LocalDate inicio, LocalDate fim) {
		
		produtoPromoDiaria.setDescricao(estoque.getDescricao());
		produtoPromoDiaria.setDtInicio(inicio);
		produtoPromoDiaria.setDtFim(fim);
		produtoPromoDiaria.setQtAtual(estoque.getQtAtual());
		produtoPromoDiaria.setIdIdentificador(estoque.getIdIdentificador());
		produtoPromoDiaria.setPrcVenda(estoque.getPrcVenda());
		produtoPromoDiaria.setVlCusto(BigDecimal.ZERO);
		produtoPromoDiaria.setVlPromocao(BigDecimal.ZERO);
		produtoPromoDiaria.setCodBarra(estoque.getCodBarra());
		produtoPromoDiaria.setCodNcm(estoque.getCodNcm());
		produtoPromoDiaria.setUniMedida(estoque.getUniMedida());
		
	}
}
