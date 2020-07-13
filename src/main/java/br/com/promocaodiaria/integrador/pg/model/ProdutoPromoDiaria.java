package br.com.promocaodiaria.integrador.pg.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name="prod_promo_diaria")
@Data
public class ProdutoPromoDiaria {
	
	public ProdutoPromoDiaria() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(name="descricao")
	private String descricao;
	
	@NonNull
	@Column(name="id_identificador")
	private Long idIdentificador;
	
	@Column(name="dt_inicio")
	private LocalDate dtInicio;
	
	@Column(name="dt_fim")
	private LocalDate dtFim;
	
	@NonNull
	@Column(name="prc_venda")
	private BigDecimal prcVenda;
	
	@Column(name="vl_custo")
	private BigDecimal vlCusto;
	
	@Column(name="vl_promocao")
	private BigDecimal vlPromocao;
	
	@Column(name="qt_atual")
	private BigDecimal qtAtual;
	
	@Column(name="cod_barra")
	private String codBarra; 
	
	@Column(name="cod_ncm")
	private String codNcm;
	
	@Column(name="uni_medida")
	private String uniMedida;
	
	@Column(name="sync")
	private boolean sync = false;
	
	@Column(name="ativo")
	private boolean ativo = false;
	
	@Column(name="log")
	private String log;
}
