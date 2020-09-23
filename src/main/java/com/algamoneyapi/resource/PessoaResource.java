package com.algamoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoneyapi.event.RecursooCriadoEvent;
import com.algamoneyapi.model.PessoaModel;
import com.algamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	
	@Autowired
	private PessoaRepository pessoaRepository;
 
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired // desparando o evento
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<PessoaModel>listar(){
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oaut2.hasSope('write')")  // para a otenticação
	public ResponseEntity<PessoaModel> criar(@Validated @RequestBody PessoaModel pessoa, HttpServletResponse response){
		
		PessoaModel pessoaSalva = pessoaRepository.save(pessoa);
		
		publisher.publishEvent(new RecursooCriadoEvent(this, response, pessoaSalva.getCodigo()));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
		
	}
	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oaut2.hasSope('read')")  // para a otenticação
	public ResponseEntity<PessoaModel> buscarPeloodigo(@PathVariable Long codigo){
		PessoaModel pessoa = pessoaRepository.getOne(codigo);
		
		return pessoa !=null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	
	//delite
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oaut2.hasSope('read')")  // para a otenticação
	@ResponseStatus(HttpStatus.NO_CONTENT)// para retorna o codigo 204 quer dizer que deletor mais ta ok 
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo); // é o delete()
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<PessoaModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody PessoaModel pessoa){
		PessoaModel pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody(required = true)Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	

	
// metodo sem utilisar a classe servisso	
//	@PostMapping("/{codigo}")  // metodo ara fazer atualização em metade do codigo
//	public ResponseEntity<PessoaModel> atualizar(@PathVariable Long codigo, @Valid @RequestBody PessoaModel pessoa){
//	PessoaModel pessoaSalva = pessoaRepository.getOne(codigo);  // fingOne()
//	BeanUtils.copyProperties(pessoa, pessoaSalva,"codigo");  // pega a pessoa que vai ser salva sem editar o codigo
//	if (pessoaSalva == null) {  // verifica se o codigo ta nulo
//		
//		throw new EmptyResultDataAccessException(1m);
//	}
//	pessoaRepository.save(pessoaSalva);
//	return ResponseEntity.ok(pessoaSalva);
//		
//	}
	
	
	

	
	
}
