package com.algamoneyapi.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

	@Id
	private Long codigo;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)// atenção relacionamento 
	@JoinTable(name = "usuario_permissao",joinColumns =  @JoinColumn(name ="codigo_usuario")
	,inverseJoinColumns = @JoinColumn(name="codigo_permissao"))
	private List<PermissaoModel>listaPermissaoModel;
	
	public UsuarioModel() {
		super();
	}



	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<PermissaoModel> getListaPermissaoModel() {
		return listaPermissaoModel;
	}

	public void setListaPermissaoModel(List<PermissaoModel> listaPermissaoModel) {
		this.listaPermissaoModel = listaPermissaoModel;
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
		UsuarioModel other = (UsuarioModel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}



	public Collection<? extends GrantedAuthority> getPermissoes(UsuarioModel usuario) {

		Set<SimpleGrantedAuthority>authorities = new HashSet<>();
		usuario.getListaPermissaoModel().forEach(p->authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		return authorities;
	}


	
}
