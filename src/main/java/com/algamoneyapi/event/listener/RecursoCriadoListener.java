package com.algamoneyapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoneyapi.event.RecursooCriadoEvent;

// é o cara que ouve o evento
@Component // para ela ser um componente
public class RecursoCriadoListener implements ApplicationListener<RecursooCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursooCriadoEvent recursoCriadoEvent) {

		HttpServletResponse  response = recursoCriadoEvent.getResponse();
		Long codigo = recursoCriadoEvent.getCodigo();
		
		adicionarHeaderLocation(response, codigo);
	}
	
private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}














