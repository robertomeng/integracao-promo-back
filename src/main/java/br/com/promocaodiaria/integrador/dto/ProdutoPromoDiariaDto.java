package br.com.promocaodiaria.integrador.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ProdutoPromoDiariaDto {

	private String idIdentificador;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private BigDecimal vlPromocao;
	private String codBarra;
	private String codNcm;
	private BigDecimal qtAtual;
	private String uniMedida;
	private LocalDate dtFim;
	private LocalDate dtInicio;
	private boolean ativo;
	private boolean sync;
	private String setor;
}
