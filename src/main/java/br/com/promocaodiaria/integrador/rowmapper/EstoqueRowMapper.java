package br.com.promocaodiaria.integrador.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.promocaodiaria.integrador.fire.model.Estoque;

public class EstoqueRowMapper implements RowMapper<Estoque>{

	@Override
	public Estoque mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estoque estoque = new Estoque();
		estoque.setDescricao(rs.getString("descricao"));
		estoque.setId(rs.getLong("id_estoque"));
		estoque.setPrcCusto(rs.getBigDecimal("prc_custo"));
		estoque.setPrcVenda(rs.getBigDecimal("prc_venda"));
		estoque.setStatus(rs.getString("status"));
		return estoque;
	}

}
