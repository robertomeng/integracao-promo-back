package br.com.promocaodiaria.integrador.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.jdbc.core.RowMapper;

import br.com.promocaodiaria.integrador.fire.model.EstoqueWrapper;

public class EstoqueWrapperRowMapper implements RowMapper<EstoqueWrapper>{

	@Override
	public EstoqueWrapper mapRow(ResultSet rs, int rowNum) throws SQLException {
		EstoqueWrapper wrapper = new EstoqueWrapper();
		wrapper.setCodBarra(rs.getString("cod_barra"));
		wrapper.setCodNcm(rs.getString("cod_ncm"));
		wrapper.setDescricao(rs.getString("descricao"));
		wrapper.setDtInicio(new LocalDateConverter().convertToEntityAttribute(rs.getDate("dt_inicio")));
		wrapper.setDtFim(new LocalDateConverter().convertToEntityAttribute(rs.getDate("dt_fim")));
		wrapper.setIdIdentificador(rs.getLong("id_identificador"));
		wrapper.setPrcVenda(rs.getBigDecimal("prc_venda"));
		wrapper.setQtdAtual(rs.getBigDecimal("qtd_atual"));
		wrapper.setUniMedida(rs.getString("uni_medida"));
		return wrapper;
	}

}
