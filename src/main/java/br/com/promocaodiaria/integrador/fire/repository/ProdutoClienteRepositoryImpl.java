package br.com.promocaodiaria.integrador.fire.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.promocaodiaria.integrador.fire.model.ProdutoClienteWrapper;
import br.com.promocaodiaria.integrador.pg.model.ProdutoPromoDiaria;
import br.com.promocaodiaria.integrador.rowmapper.ProdutoClienteWrapperRowMapper;
import br.com.promocaodiaria.integrador.service.strategy.QueryStrategy;

@Repository
public class ProdutoClienteRepositoryImpl implements ProdutoClienteRepositoryCustom {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	QueryStrategy queryStrategy;
	
	public List<ProdutoClienteWrapper> findProdutoClienteByDescricao(String query) {
		
		Map<String,Object> params = new HashMap<String,Object>();
	    params.put("query", query);

	    NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
	    
	    return npjt.query(queryStrategy.selectProdutosPorDescricao(), params, new ProdutoClienteWrapperRowMapper());
	}
	
	public ProdutoClienteWrapper findProdutoClienteById(Long id) {
		
		Map<String,Object> params = new HashMap<String,Object>();
	    params.put("id", id);

	    NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
	    
	    return npjt.queryForObject(queryStrategy.selectProdutosPorId(), params, new ProdutoClienteWrapperRowMapper());
	}

	@Override
	public ProdutoClienteWrapper getProdutoAlterado(ProdutoPromoDiaria produtoPromoDiaria) {
		Map<String,Object> params = new HashMap<String,Object>();
	    
		params.put("idIdentificador", produtoPromoDiaria.getIdIdentificador());
	    params.put("qtAtual", produtoPromoDiaria.getQtAtual());
	    params.put("codBarra", produtoPromoDiaria.getCodBarra());
	    params.put("codNcm", produtoPromoDiaria.getCodNcm());
	    params.put("nome", produtoPromoDiaria.getNome());
	    params.put("valor", produtoPromoDiaria.getValor());
	    params.put("dtInicio", produtoPromoDiaria.getDtInicio());
	    params.put("dtFim", produtoPromoDiaria.getDtFim());
	    params.put("valorPromocao", produtoPromoDiaria.getVlPromocao());
	    params.put("uniMedida", produtoPromoDiaria.getUniMedida());
	    params.put("descricao", produtoPromoDiaria.getDescricao());
	    
	    NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
	    
	    return npjt.queryForObject(queryStrategy.selectProdutoAlterado(), params, new ProdutoClienteWrapperRowMapper());
	}
}
