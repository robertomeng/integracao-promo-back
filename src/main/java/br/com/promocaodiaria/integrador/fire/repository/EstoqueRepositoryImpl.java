package br.com.promocaodiaria.integrador.fire.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.promocaodiaria.integrador.fire.model.EstoqueWrapper;
import br.com.promocaodiaria.integrador.query.Querys;
import br.com.promocaodiaria.integrador.rowmapper.EstoqueWrapperRowMapper;

@Repository
public class EstoqueRepositoryImpl implements EstoqueRepositoryCustom {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<EstoqueWrapper> findEstoqueByName(String query) {
		
		Map<String,Object> params = new HashMap<String,Object>();
	    params.put("query", query);

	    NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
	    List<EstoqueWrapper> lista = npjt.query(Querys.select_produtos, params, new EstoqueWrapperRowMapper());
		
		return lista;
	}

}
