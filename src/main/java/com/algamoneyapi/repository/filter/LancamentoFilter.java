package com.algamoneyapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {

	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimnetoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ataVencimentoAte;

	
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimnetoDe() {
		return dataVencimnetoDe;
	}

	public void setDataVencimnetoDe(LocalDate dataVencimnetoDe) {
		this.dataVencimnetoDe = dataVencimnetoDe;
	}

	public LocalDate getAtaVencimentoAte() {
		return ataVencimentoAte;
	}

	public void setAtaVencimentoAte(LocalDate ataVencimentoAte) {
		this.ataVencimentoAte = ataVencimentoAte;
	}
	
	
	
}
