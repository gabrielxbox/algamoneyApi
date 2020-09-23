package com.algamoneyapi.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

                                          // do ispring 
public class RecursooCriadoEvent extends ApplicationEvent {


	//a qui é para controla o evento sem pressivar utilizaer o evento no controle
	
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	
	private Long codigo;
	
	public RecursooCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);

		this.response = response;
		
		this.codigo = codigo;
		
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}


	
	
}














