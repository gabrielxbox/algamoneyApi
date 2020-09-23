package com.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> { // pasa o model e o tipo do id 
//  metodos ja prontos


}
