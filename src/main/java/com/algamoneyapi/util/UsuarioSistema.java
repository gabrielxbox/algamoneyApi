package com.algamoneyapi.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algamoneyapi.model.UsuarioModel;

public class UsuarioSistema extends User {


	/**  
	 * o usuariosistema tem tambem um usuario
	 */
	private static final long serialVersionUID = 1L;

	private UsuarioModel usuario;
	
	public UsuarioSistema(UsuarioModel usuario,Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(),usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}
	
}
