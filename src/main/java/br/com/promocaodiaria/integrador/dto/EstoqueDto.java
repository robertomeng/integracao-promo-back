package br.com.promocaodiaria.integrador.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EstoqueDto {

	private Long id;
	private String descricao;
	private String status;
	private BigDecimal prcCusto;
	private BigDecimal prcVenda;
	private List<LocalDate> datas = new ArrayList<LocalDate>();
	private boolean ativar;
}
