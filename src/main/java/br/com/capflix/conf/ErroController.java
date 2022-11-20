package br.com.capflix.conf;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import  org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.capflix.exception.NegocioException;
import br.com.capflix.exception.TabelaDeErros;
import br.com.capflix.model.dto.ErroSaidaDto;

@RestControllerAdvice
public class ErroController {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NegocioException.class)
	@ResponseBody
	public ResponseEntity<ErroSaidaDto> handle(NegocioException exception) {
		ErroSaidaDto dto = new ErroSaidaDto();
		dto.setCodigoDeErro(exception.getCodigoDeErro());
		dto.setMensagem(exception.getMensagem());
		
		return ResponseEntity.status(exception.getCodigoHttp()).body(dto);
	}
	
	//validacao: @RequestBody @Valid
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResponseEntity<ErroSaidaDto> handle(BindException exception) {
		List<String> validacoes = new ArrayList<>();

		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			validacoes.add(error.getField()+": "+mensagem);
		}
		
		TabelaDeErros tabela = TabelaDeErros.VALIDACAO;
		
		ErroSaidaDto dto = new ErroSaidaDto();
		dto.setCodigoDeErro(tabela.getCodigoDeErro());
		dto.setMensagem(tabela.getMensagem());
		dto.setValidacoes(validacoes);
		
		return ResponseEntity.status(tabela.getCodigoHttp()).body(dto);
	}
	
	//validacao: parametro
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<ErroSaidaDto> handle(ConstraintViolationException e) {
		List<String> validacoes = new ArrayList<>();

		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
			validacoes.add(path + ": " + violation.getMessage());
		}

		TabelaDeErros tabela = TabelaDeErros.VALIDACAO;
		
		ErroSaidaDto dto = new ErroSaidaDto();
		dto.setCodigoDeErro(tabela.getCodigoDeErro());
		dto.setMensagem(tabela.getMensagem());
		dto.setValidacoes(validacoes);
		
		return ResponseEntity.status(tabela.getCodigoHttp()).body(dto);
	}
}
