package br.com.promocaodiaria.integrador.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPromoDiariaDto {

	@NonNull
	private Long idIdentificador;
	private List<LocalDate> datas = new ArrayList<>();
}
