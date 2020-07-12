package br.com.promocaodiaria.integrador.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NonNull;

@Data
public class ProdutoPromoDiariaDto {

	@NonNull
	private Long idIdentificador;
	private String descricao;
	private BigDecimal prcVenda;
	private BigDecimal vlCusto;
	private BigDecimal vlPromocao;
	private boolean ativo;
	private String codBarra;
	private String codNcm;
	private BigDecimal qtAtual;
	private String uniMedida;
	private boolean sync;
	private LocalDate dtInicio;
	private LocalDate dtFim;
}
