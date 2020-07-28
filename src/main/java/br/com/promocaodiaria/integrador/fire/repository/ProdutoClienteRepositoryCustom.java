package br.com.promocaodiaria.integrador.fire.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.promocaodiaria.integrador.fire.model.ProdutoClienteWrapper;
import br.com.promocaodiaria.integrador.pg.model.ProdutoPromoDiaria;

@Repository
public interface ProdutoClienteRepositoryCustom {

	public List<ProdutoClienteWrapper> findProdutoClienteByDescricao(String query);
	public ProdutoClienteWrapper findProdutoClienteById(String id);
	public ProdutoClienteWrapper getProdutoAlterado(ProdutoPromoDiaria produtoPromoDiaria);
}
