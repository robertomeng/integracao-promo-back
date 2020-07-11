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
	@Column(name="id_produto_cliente")
	private Long idProdutoCliente;
	
	@Column(name="dt_inicio")
	private LocalDate dtInicio;
	
	@Column(name="dt_fim")
	private LocalDate dtFim;
	
	@NonNull
	@Column(name="valor")
	private BigDecimal valor;
	
	@Column(name="vl_custo")
	private BigDecimal vlCusto;
	
	@Column(name="vl_promocao")
	private BigDecimal vlPromocao;
	
	@Column(name="estoque")
	private BigDecimal estoque;
	
	@Column(name="cod_barra")
	private String codBarra; 
	
	@Column(name="cod_ncm")
	private String codNcm;
	
	@Column(name="uni_medida")
	private String uniMedida;
	
	@Column(name="sync")
	private boolean sync = false;
}
