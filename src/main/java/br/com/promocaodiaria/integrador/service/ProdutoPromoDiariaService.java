package br.com.promocaodiaria.integrador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.promocaodiaria.integrador.dto.ProdutoPromoDiariaDto;
import br.com.promocaodiaria.integrador.fire.model.ProdutoClienteWrapper;
import br.com.promocaodiaria.integrador.fire.repository.ProdutoClienteRepository;
import br.com.promocaodiaria.integrador.pg.model.ProdutoPromoDiaria;
import br.com.promocaodiaria.integrador.pg.repository.ProdutoPromoDiariaRepository;

@Service
public class ProdutoPromoDiariaService {

	@Autowired
	ProdutoPromoDiariaRepository produtoPromoDiariaRepository;
	
	@Autowired
	ProdutoClienteRepository estoqueRepository;
	
	@Autowired
	IntegracaoPromocaoDiariaService integracaoPromocaoDiariaService;
	
	public void save(List<ProdutoPromoDiariaDto> produtos) {
		
		produtos.forEach(produtoDto -> {
			
			ProdutoClienteWrapper produtoCliente = estoqueRepository.findProdutoClienteById(produtoDto.getIdIdentificador());
			
			ProdutoPromoDiaria produto = new ProdutoPromoDiaria();
			
			parserProduto(produto, produtoCliente);
			
			produto.setSetor(produtoDto.getSetor());
			
			Response response = integracaoPromocaoDiariaService.adicionarProduto(produto);
			
			verifyResponse(response, produto);
			
			produtoPromoDiariaRepository.save(produto);
		});
	}
	
	public ProdutoPromoDiaria update(ProdutoPromoDiaria produto, ProdutoClienteWrapper produtoCliente, boolean updateOnlyStock) {
		try {
			
			Response response = null;
			
			if(updateOnlyStock) {

				produto.setQtAtual(produtoCliente.getQtAtual());
				response = integracaoPromocaoDiariaService.baixaEstoque(produto);
				
			} else {
				parserProduto(produto, produtoCliente);			
				response = integracaoPromocaoDiariaService.editarProduto(produto);
			}	
		
			verifyResponse(response, produto);
		
		} catch(Exception e) {
			
			produto.setSync(false);
			produto.setLog(e.getMessage());
			
		}
		
		return produtoPromoDiariaRepository.saveAndFlush(produto);	
	}
	
	private void verifyResponse(Response response, ProdutoPromoDiaria produto) {
		
		if("OK".equalsIgnoreCase(response.getStatus())) {
			
			produto.setSync(true);
			produto.setLog(null);
		
		} else {
			produto.setSync(false);
			produto.setLog(response.getMensagem());
		}
	}
	
	private void parserProduto(ProdutoPromoDiaria produtoPromoDiaria, ProdutoClienteWrapper produtoCliente) {
		
		produtoPromoDiaria.setIdIdentificador(produtoCliente.getIdIdentificador());
		produtoPromoDiaria.setNome(produtoCliente.getNome());
		produtoPromoDiaria.setDescricao(produtoCliente.getDescricao());
		produtoPromoDiaria.setQtAtual(produtoCliente.getQtAtual());
		produtoPromoDiaria.setCodBarra(produtoCliente.getCodBarra());
		produtoPromoDiaria.setCodNcm(produtoCliente.getCodNcm());
		produtoPromoDiaria.setValor(produtoCliente.getValor());
		produtoPromoDiaria.setDtInicio(produtoCliente.getDtInicio());
		produtoPromoDiaria.setDtFim(produtoCliente.getDtFim());
		produtoPromoDiaria.setVlPromocao(produtoCliente.getVlPromocao());
		produtoPromoDiaria.setUniMedida(produtoCliente.getUniMedida());
		produtoPromoDiaria.setAtivo(produtoCliente.getAtivo());
		
	}
}
