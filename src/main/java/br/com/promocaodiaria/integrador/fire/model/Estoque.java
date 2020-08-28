package br.com.promocaodiaria.integrador.fire.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_estoque")
@Data
public class Estoque implements Serializable {

	private static final long serialVersionUID = -483542444206996480L;

	@Id
	@Column(name = "id_estoque")
	private Long id;
	private String descricao;
	private String status;
	private double prcCusto;
	private double prcVenda;

}
