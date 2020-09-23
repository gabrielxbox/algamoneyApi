package com.algamoneyapi.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pessoa")
public class PessoaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name ="codigo")
	private Long codigo;
	
	@Embedded // como se fosse um eqistend
	private EnderecoModel enderecoModel; 
	
	@NotNull
	@Column(name = "ativo")
    private Boolean ativo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public EnderecoModel getEnderecoModel() {
		return enderecoModel;
	}

	public void setEnderecoModel(EnderecoModel enderecoModel) {
		this.enderecoModel = enderecoModel;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	

	@JsonIgnore // anotação para ingnora quenem o @temporal
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}
	
}


































