package com.algamoneyapi.service;

import javax.swing.plaf.MenuBarUI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algamoneyapi.model.PessoaModel;
import com.algamoneyapi.repository.PessoaRepository;

@Service // classe de serviços do is pring utilizada para controla as regras de negosso
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public PessoaModel atualizar(Long codigo, PessoaModel pessoa) {
		
		PessoaModel pessoaSalva = buscaPessoaPeloCodigo(codigo);
		return pessoaRepository.save(pessoaSalva);
		
	}

	
	// a qui é atualização paciacial e a propiedade é ativo
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void  atualizarPropriedadeAtivo( Long codigo, Boolean ativo) {
		
		PessoaModel pessoaSalva = buscaPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
		
	}
	 
	public PessoaModel buscaPessoaPeloCodigo(Long codigo) {
		PessoaModel pessoaSalva = pessoaRepository.getOne(codigo);  // fingOne()

		if (pessoaSalva == null) {  // verifica se o codigo ta nulo
			
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

}
