package com.algamoneyapi.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.algamoneyapi.model.UsuarioModel;
import com.algamoneyapi.repository.UsuarioRepository;

public class AppUserDatailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   Optional<UsuarioModel> usuarOptional = usuarioRepository.findByEmail(email);
   UsuarioModel usuario = usuarOptional.orElseThrow(()-> new UsernameNotFoundException("Usuario e/ou senaha"));
		return new UsuarioSistema(usuario, usuario.getPermissoes(usuario));
	}

	
}
