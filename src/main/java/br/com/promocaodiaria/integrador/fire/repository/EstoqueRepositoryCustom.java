package br.com.promocaodiaria.integrador.fire.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.promocaodiaria.integrador.fire.model.EstoqueWrapper;

@Repository
public interface EstoqueRepositoryCustom {

	public List<EstoqueWrapper> findEstoqueByDescricao(String query);
	public EstoqueWrapper findEstoqueById(Long id);
}
