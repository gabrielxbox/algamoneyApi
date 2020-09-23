package com.algamoneyapi.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.LancamentoModel;
import com.algamoneyapi.model.PessoaModel;
import com.algamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.service.exception.PrssoaInexistenteOuInativaException;

// para nao enserir lansamento pessoa sem ta ativa 
@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public LancamentoModel salvar(@Valid LancamentoModel lancamento) {

		PessoaModel pessoa = pessoaRepository.getOne(lancamento.getPessoaModel().getCodigo());

		if (pessoa == null || pessoa.isInativo()) {

			throw new PrssoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}

	public LancamentoModel atualizar(Long codigo, LancamentoModel lancamento) {
      LancamentoModel lancamentoSalvo = buscarLancamentoExistente(codigo);
	   if(!lancamento.getPessoaModel().equals(lancamento.getPessoaModel())) {
		 validarPessoa(lancamento);  
	   }
	   //  BeanUtil função que copia um model para outro        so nao copia o codigo
	   BeanUtils.copyProperties(lancamento, lancamentoSalvo,"codigo");
	   
	   return lancamentoRepository.save(lancamentoSalvo);
	}

	// trocando peassoa do lancamento
	
	private void validarPessoa(LancamentoModel lancamento) {
        PessoaModel pessoa = null;
        if(lancamento.getPessoaModel().getCodigo() !=null) {
        	pessoa = pessoaRepository.getOne(lancamento.getPessoaModel().getCodigo());
        	
        }
		
        if(pessoa == null || pessoa.isInativo()){
        throw new PrssoaInexistenteOuInativaException();
        }
	}

	private LancamentoModel buscarLancamentoExistente(Long codigo) {

		LancamentoModel lancamentoSalvo = lancamentoRepository.getOne(codigo);
		if(lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}
}









