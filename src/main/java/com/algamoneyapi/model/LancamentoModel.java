package com.algamoneyapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lancamento")
public class LancamentoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long codigo;

	private String descricao;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimneto; /// variavel de tada do java 8

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;
	@NotNull
	private BigDecimal valor;
	@NotNull
	private String observacao;

	@NotNull
	@Enumerated(EnumType.STRING) // para salvar um tipo
	private TipoLancamento tipo;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private CategoriaModel categoriaModel;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private PessoaModel pessoaModel;

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

	public LocalDate getDataVencimneto() {
		return dataVencimneto;
	}

	public void setDataVencimneto(LocalDate dataVencimneto) {
		this.dataVencimneto = dataVencimneto;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public CategoriaModel getCategoriaModel() {
		return categoriaModel;
	}

	public void setCategoriaModel(CategoriaModel categoriaModel) {
		this.categoriaModel = categoriaModel;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	public LancamentoModel() {
		super();
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
		LancamentoModel other = (LancamentoModel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
