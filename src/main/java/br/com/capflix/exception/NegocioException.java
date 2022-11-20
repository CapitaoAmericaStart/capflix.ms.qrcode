package br.com.capflix.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	private final HttpStatus codigoHttp;
	private final String codigoDeErro;
	private final String mensagem;
    
    public NegocioException(TabelaDeErros tabela) {
		this.codigoHttp = tabela.getCodigoHttp();
		this.codigoDeErro = tabela.getCodigoDeErro();
		this.mensagem = tabela.getMensagem();
    }
}