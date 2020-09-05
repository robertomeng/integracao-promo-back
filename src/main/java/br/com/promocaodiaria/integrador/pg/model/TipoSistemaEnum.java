package br.com.promocaodiaria.integrador.pg.model;

public enum TipoSistemaEnum {

	TGA("tga"),
	CLIPP("clipp"),
	ECOSYS("ecosys"),
	CELTA("celta");
	
	private String nome;
	
	TipoSistemaEnum(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
