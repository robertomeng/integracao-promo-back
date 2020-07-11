package br.com.promocaodiaria.integrador.fire.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.promocaodiaria.integrador.fire.model.EstoqueWrapper;
import br.com.promocaodiaria.integrador.query.Queries;
import br.com.promocaodiaria.integrador.rowmapper.EstoqueWrapperRowMapper;

@Repository
public class EstoqueRepositoryImpl implements EstoqueRepositoryCustom {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<EstoqueWrapper> findEstoqueByDescricao(String query) {
		
		Map<String,Object> params = new HashMap<String,Object>();
	    params.put("query", query);

	    NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
	    
	    return npjt.query(Queries.select_produtos_por_descricao, params, new EstoqueWrapperRowMapper());
	}
	
	public EstoqueWrapper findEstoqueById(Long id) {
		
		Map<String,Object> params = new HashMap<String,Object>();
	    params.put("id", id);

	    NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
	    
	    return npjt.queryForObject(Queries.select_produtos_por_id, params, new EstoqueWrapperRowMapper());
	}
}
