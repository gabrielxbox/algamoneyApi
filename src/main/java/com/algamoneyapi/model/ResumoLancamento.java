package com.algamoneyapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumoLancamento {

	
	private Long codigo;
	
	private String descricao;
	
	private LocalDate dataVencimeto;
	
	private LocalDate dataPagamento;
	
	private BigDecimal valor;
	
	private TipoLancamento tipo;
	
	private String categoria;
	
	private String pessoa;

	public ResumoLancamento() {
		super();
	}

	
	
	public ResumoLancamento(Long codigo, String descricao, LocalDate dataVencimeto, LocalDate dataPagamento,
			BigDecimal valor, TipoLancamento tipo, String categoria, String pessoa) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataVencimeto = dataVencimeto;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.tipo = tipo;
		this.categoria = categoria;
		this.pessoa = pessoa;
	}



	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimeto() {
		return dataVencimeto;
	}

	public void setDataVencimeto(LocalDate dataVencimeto) {
		this.dataVencimeto = dataVencimeto;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResumoLancamento other = (ResumoLancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
