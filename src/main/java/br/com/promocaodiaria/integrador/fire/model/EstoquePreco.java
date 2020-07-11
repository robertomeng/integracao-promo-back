package br.com.promocaodiaria.integrador.fire.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_estoque_precos")
@Data
public class EstoquePreco {

	@Id
	private Long idIdentificador;
	private LocalDate dtIncio; 
	private LocalDate dtFim; 
	private BigDecimal prcVenda;
}
