package br.com.capflix.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmeClientSaidaDto {

	private Long id;
	private String nome;
	private String descricao;
	private String url;
	private String genero;
}
