package br.com.promocaodiaria.integrador.fire.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class EstoqueWrapper {

	private Long idIdentificador;
	private BigDecimal qtdAtual;
	private String codBarra; 
	private String codNcm; 
	private String descricao;
	private BigDecimal prcVenda;
	private String uniMedida;
	private LocalDate dtInicio; 
	private LocalDate dtFim;
}
