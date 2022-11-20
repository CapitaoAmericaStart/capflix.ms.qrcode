package br.com.capflix.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroSaidaDto {

	private String codigoDeErro;
	private String mensagem;
	
	private List<String> validacoes;
}
