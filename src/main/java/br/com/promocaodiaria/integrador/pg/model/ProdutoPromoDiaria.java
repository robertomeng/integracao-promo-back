package br.com.promocaodiaria.integrador.pg.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	@Column(name="nome")
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@NonNull
	@Column(name="id_identificador")
	private String idIdentificador;
	
	@Column(name="dt_inicio")
	private LocalDateTime dtInicio;
	
	@Column(name="dt_fim")
	private LocalDateTime dtFim;
	
	@NonNull
	@Column(name="valor")
	private BigDecimal valor;
	
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
	
	@Column(name="setor")
	private String setor;
}
