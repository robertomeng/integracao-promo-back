package br.com.promocaodiaria.integrador.fire.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ProdutoClienteWrapper {

	private Long idIdentificador;
	private BigDecimal qtAtual;
	private String codBarra; 
	private String codNcm; 
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private LocalDate dtInicio;
	private LocalDate dtFim;
	private BigDecimal vlPromocao;
	private String uniMedida;
}
