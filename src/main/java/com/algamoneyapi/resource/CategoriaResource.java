package com.algamoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algamoneyapi.event.RecursooCriadoEvent;
import com.algamoneyapi.model.CategoriaModel;
import com.algamoneyapi.repository.CategoriaRepository;

@RestController // transforma em jeson
@RequestMapping("/categorias") // mapeando o model
//@CrossOrigin(maxAge = 10, origins = {"http://localhost:8080"}) para a class  ainda nao ta tao bom por causa do sprin oauth
public class CategoriaResource {

	// chamada do reppository categoria
	@Autowired 
	private CategoriaRepository categoriaRepository;

	@Autowired // desparando o evento
	private ApplicationEventPublisher publisher;
	
	// permite fazer requisição de porta e ip diferente  @CrossOrigin(maxAge = 10 = tempo para fazer
	//  origins = {"http://localhost:8080"})  qual é a origen da requesição
	
   // @CrossOrigin(maxAge = 10, origins = {"http://localhost:8080"})  ainda nao ta tao bom por causa do sprin oauth
	// tras uma lista
	@GetMapping
	public List<CategoriaModel> listar() {
		return categoriaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<CategoriaModel> criar(@Valid@RequestBody CategoriaModel categoria, HttpServletResponse response) {
		
		CategoriaModel categoriaSalva = categoriaRepository.save(categoria);
		
		publisher.publishEvent(new RecursooCriadoEvent(this, response, categoria.getCodigo()));
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
//	@PostMapping
//	// @ResponseStatus(HttpStatus.CREATED)// para retorna o estado de criado
//	public ResponseEntity<CategoriaModel> criar(@RequestBody CategoriaModel categoria, HttpServletResponse response){ // cria categorias    @RequestBody para reconheser a propiedade do model
//	CategoriaModel categoriaSalva = categoriaRepository.save(categoria);
//	URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
//	response.setHeader("Location",uri.toASCIIString());
//	
//	return ResponseEntity.created(uri).body(categoriaSalva);
//	
//	}
	
	
	
	 @GetMapping("/{codigo}")
	public ResponseEntity<CategoriaModel> budcarPeloCodigo(@PathVariable Long codigo){
		 
		 CategoriaModel categoria = categoriaRepository.getOne(codigo);   //getOne
		 return categoria!=null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	 }
	
	
	
	
	
	

//	public ResponseEntity<?> listar(){  // para retorno o erro se a lista tiver vazia   o notfalnd() é para retorno o erro 400 para mostra que a lista é nula
//		List<CategoriaModel>categorias =categoriaRepository.findAll();
//		return !categorias.isEmpty() ? ResponseEntity.ok(categorias)  : ResponseEntity.noContent().build();  // verificando se ta com gategoria ou vazia 404
//	}  // noContent() vala que a consulta ta serta mais ta vazia erro 204	
//	

	
	
}

