package br.com.promocaodiaria.integrador.pg.model;

public enum TipoSistemaEnum {

	TGA("tga"),
	CLIPP("clipp");
	
	private String nome;
	
	TipoSistemaEnum(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
