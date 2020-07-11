package br.com.promocaodiaria.integrador.fire.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_EST_PRODUTO")
@Data
public class EstoqueProduto {

	@Id
	private Long idIdentificador;
	private BigDecimal qtdAtual;
	private String codBArra;
	private String codNcm;
}
