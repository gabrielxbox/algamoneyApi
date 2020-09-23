package com.algamoneyapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.algamoneyapi.model.UsuarioModel;
import com.algamoneyapi.repository.UsuarioRepository;

@ServiceMode
public class AppUserDetailsService implements UserDetailsService{
// para valida na ora de loga no sistema 
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override   
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByEmail(email);
           UsuarioModel  usuario =  usuarioOptional.orElseThrow( ()-> new UsernameNotFoundException("Usuario e senha incorretos"));
		return new User(email, usuario.getSenha(), getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(UsuarioModel usuario) {
// para saber quais sao as permisoes 
		
	//	implementação do java 8 forEach
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getListaPermissaoModel().forEach(p-> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		
		return authorities;
	}

	
}
