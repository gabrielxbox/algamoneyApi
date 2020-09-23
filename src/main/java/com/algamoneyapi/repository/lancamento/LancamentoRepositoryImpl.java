package com.algamoneyapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.algamoneyapi.model.CategoriaModel_;
import com.algamoneyapi.model.LancamentoModel;
import com.algamoneyapi.model.LancamentoModel_;
import com.algamoneyapi.model.ResumoLancamento;
import com.algamoneyapi.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<LancamentoModel> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<LancamentoModel> criteria = builder.createQuery(LancamentoModel.class);
		// o root ele faz os filtros do metodo criteria
		Root<LancamentoModel> root = criteria.from(LancamentoModel.class);

		// criar as restriçoes
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<LancamentoModel> query = manager.createQuery(criteria);

		adicionarRestricoesDePaginacao(query, pageable);

		return new  PageImpl<>(query.getResultList(),pageable,total(lancamentoFilter));
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<LancamentoModel> root) {

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(LancamentoModel_.descricao)),
					"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(lancamentoFilter.getDataVencimnetoDe())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(LancamentoModel_.dataVencimneto),
					lancamentoFilter.getDataVencimnetoDe()));
		}

		if (!StringUtils.isEmpty(lancamentoFilter.getAtaVencimentoAte())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(LancamentoModel_.dataVencimneto),
					lancamentoFilter.getAtaVencimentoAte()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(LancamentoFilter lancamentoFilter) {
 
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		 Root<LancamentoModel> root = criteria.from(LancamentoModel.class);
		 
		 Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		 criteria.where(predicates);
		 
		 criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
          CriteriaBuilder builder =manager.getCriteriaBuilder();
          CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
          Root<LancamentoModel > root = criteria.from(LancamentoModel.class);
          
          criteria.select(builder.construct(ResumoLancamento.class
        		  ,root.get(LancamentoModel_.codigo), root.get(LancamentoModel_.descricao)
        		  ,root.get(LancamentoModel_.dataVencimneto), root.get(LancamentoModel_.dataPagamento)
        		  ,root.get(LancamentoModel_.valor), root.get(LancamentoModel_.tipo)
        		  ,root.get(LancamentoModel_.categoriaModel).get(CategoriaModel_.nome)));
          // nao tem nome   ,root.get(LancamentoModel_.pessoaModel).get(PessoaModel_.nome)
      	  
          Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
 		 criteria.where(predicates);
          
          TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
	            adicionarRestricoesDePaginacao(query, pageable);
	            
		return new  PageImpl<>(query.getResultList(),pageable,total(lancamentoFilter));		
	}

}











































