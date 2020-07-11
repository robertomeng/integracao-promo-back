package br.com.promocaodiaria.integrador.pg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.promocaodiaria.integrador.pg.model.ProdutoPromoDiaria;

@Repository
public interface ProdutoPromoDiariaRepository extends JpaRepository<ProdutoPromoDiaria, Long> {

}
