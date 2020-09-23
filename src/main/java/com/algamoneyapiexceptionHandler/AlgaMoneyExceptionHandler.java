package com.algamoneyapiexceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // é um controle e vai observa a plicação
public class AlgaMoneyExceptionHandler extends ResponseEntityExceptionHandler{// capitura execao das entidade

	// obter a mensagem do mensagen
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
 
		String messagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
		
		String mensagemDsenvolvedor = ex.getCause() !=null ? ex.getCause().toString() : ex.toString(); 
		
		List<Erro> erros =Arrays.asList(new Erro(messagemUsuario, mensagemDsenvolvedor));
		
		return handleExceptionInternal(ex,  erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	//trata argumento nao valido
	@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Erro> erros  = criaListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	// metodo que vai tratar erro quando deleta o mesmo id 2 ves
	
	@ExceptionHandler({EmptyResultDataAccessException.class}) // AS CLASSE QUE DEVE TRATAR  
//	@ResponseStatus(HttpStatus.NOT_FOUND)// retorna o erro 404
	public ResponseEntity<Object> handleEmptyResultDataAccessException( EmptyResultDataAccessException ex, WebRequest request) {  // mensagem de erro gerada 
		

		String messagemUsuario = messageSource.getMessage("recurso.nao-encontrado",null, LocaleContextHolder.getLocale());
			
			String mensagemDsenvolvedor = ex.toString(); 
			
			List<Erro> erros =Arrays.asList(new Erro(messagemUsuario, mensagemDsenvolvedor));
			
		return handleExceptionInternal(ex,  erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	// metodo para tratar A exeção da data
	@ExceptionHandler({ DataIntegrityViolationException.class})
	public ResponseEntity<Object>handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request ){
		
		String messagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida",null, LocaleContextHolder.getLocale());
		
		String mensagemDsenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		
		List<Erro> erros =Arrays.asList(new Erro(messagemUsuario, mensagemDsenvolvedor));
		
	return handleExceptionInternal(ex,  erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	
	private List<Erro>criaListaDeErros(BindingResult bindingResult){  //bindingResult lista de erros
		List<Erro>erros = new  ArrayList<>();
		for (FieldError fieldError :bindingResult.getFieldErrors()) { //retorna um FieldError
     String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
     String mensagemDesenvolvedor = fieldError.toString();
     
     erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		}
		return erros;
	}
	
	public static class Erro {
		private  String  messagemUsuario;
		private  String  mensagemDsenvolvedor;
		public String getMessagemUsuario() {
			return messagemUsuario;
		} 
		public void setMessagemUsuario(String messagemUsuario) {
			this.messagemUsuario = messagemUsuario;
		}
		public String getMensagemDsenvolvedor() {
			return mensagemDsenvolvedor;
		}
		public void setMensagemDsenvolvedor(String mensagemDsenvolvedor) {
			this.mensagemDsenvolvedor = mensagemDsenvolvedor;
		}
		public Erro(String messagemUsuario, String mensagemDsenvolvedor) {
			super();
			this.messagemUsuario = messagemUsuario;
			this.mensagemDsenvolvedor = mensagemDsenvolvedor;
		}
		
		
		
	}
}





















