package br.com.capflix.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {

	VALIDACAO(HttpStatus.BAD_REQUEST, "1002-1000", "dados de requisição inválidos"),
	IMAGEM_NAO_ENCONTRADA(HttpStatus.NOT_FOUND, "1002-2000", "imagem não encontrada"),	
	ERRO_NA_URL_DA_IMAGEM(HttpStatus.PRECONDITION_FAILED, "1002-3000", "erro na url da imagem"),
	NO_DIRETORIO_DA_IMAGEM(HttpStatus.INTERNAL_SERVER_ERROR, "1002-5001", "no diretorio da imagem"),
	SISTEMA(HttpStatus.INTERNAL_SERVER_ERROR, "1002-5000", "sitema indisponível");
	
	private final HttpStatus codigoHttp;
	private final String codigoDeErro;
	private final String mensagem;
}
