package br.com.promocaodiaria.integrador.fire.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.promocaodiaria.integrador.fire.model.Estoque;

@Repository
public interface ProdutoClienteRepository extends JpaRepository<Estoque, Long>, ProdutoClienteRepositoryCustom {

	@Query(value = "select e from Estoque e where e.descricao like %:query%")
	List<Estoque> search(@Param("query") String query, Pageable page);
}
