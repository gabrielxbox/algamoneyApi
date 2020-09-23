package com.algamoneyapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algamoneyapi.event.RecursooCriadoEvent;
import com.algamoneyapi.model.LancamentoModel;
import com.algamoneyapi.model.ResumoLancamento;
import com.algamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.repository.filter.LancamentoFilter;
import com.algamoneyapi.service.LancamentoService;
import com.algamoneyapi.service.exception.PrssoaInexistenteOuInativaException;
import com.algamoneyapiexceptionHandler.AlgaMoneyExceptionHandler.Erro;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageSource messageSource;

//	// buscar
//	@GetMapping
//	public List<LancamentoModel> listar() {
//		return lancamentoRepository.findAll();
//	}

	@GetMapping // para Pageable cria uma parginação tem que ser do spring
	public Page<LancamentoModel> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}

	@GetMapping (params="resumo")
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
	}
	
	// salvar

	@PostMapping
	public ResponseEntity<LancamentoModel> criar(@Valid @RequestBody LancamentoModel lancamento,
			HttpServletResponse response) {

		LancamentoModel lancamentoSalva = lancamentoService.salvar(lancamento);

		publisher.publishEvent(new RecursooCriadoEvent(this, response, lancamento.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
	}
	
	

	@GetMapping("/codigo")
	public ResponseEntity<LancamentoModel> buscarPeloCodigo(@PathVariable Long codigo) {
		LancamentoModel lancamento = lancamentoRepository.getOne(codigo);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	

	// tratando a exelao de nao ter uma pessoa inativa
	@ExceptionHandler({ PrssoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> PrssoaInexistenteOuInativaException(PrssoaInexistenteOuInativaException ex) {

		String messagemUsuario = messageSource.getMessage("pessoa.inesxixtente-ou-inativa", null,
				LocaleContextHolder.getLocale());
		String mensagemDsenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, mensagemDsenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

	// deleta
	
//	@DeleteMapping("/{codigo}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void remover(@PathVariable Long codigo) {
//		lancamentoRepository.deleteById(codigo);
//	}

	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
	public ResponseEntity<LancamentoModel>atualizar(@PathVariable Long codigo, @Valid @RequestBody LancamentoModel lancamento){
		try {
			LancamentoModel lancamentoSalvo = lancamentoService.atualizar(codigo, lancamento);
			return ResponseEntity.ok(lancamentoSalvo);
		} catch (IllegalArgumentException e) {
          return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
}
