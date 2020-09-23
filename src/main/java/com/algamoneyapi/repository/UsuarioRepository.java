package com.algamoneyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
// tem que ser de interface para implementa o JpaRepository
	
	public Optional<UsuarioModel> findByEmail(String email);
	
}
