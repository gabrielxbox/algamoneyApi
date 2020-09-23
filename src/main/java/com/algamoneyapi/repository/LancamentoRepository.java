package com.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.LancamentoModel;
import com.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, Long>,  LancamentoRepositoryQuery{

}
