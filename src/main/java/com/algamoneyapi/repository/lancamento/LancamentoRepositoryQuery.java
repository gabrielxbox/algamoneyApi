package com.algamoneyapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algamoneyapi.model.LancamentoModel;
import com.algamoneyapi.model.ResumoLancamento;
import com.algamoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public Page<LancamentoModel>filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

    public Page<ResumoLancamento>resumir(LancamentoFilter lancamentoFilter,Pageable pageable);

}
