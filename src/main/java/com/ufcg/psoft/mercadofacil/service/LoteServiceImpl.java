package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.LoteRepository;

@Service
public class LoteServiceImpl implements LoteService {
	
	@Autowired
	private LoteRepository loteRepository;
	
	public List<Lote> listarLotes() {
		return loteRepository.findAll();
	}

	public void salvarLote(Lote lote) {
		loteRepository.save(lote);		
	}

	public Lote criaLote(int numItens, Produto produto, String data) {
		Lote lote = new Lote(produto, numItens, data);
		return lote;
	}

	/**
	 * editar  data dos lotes
	 */
	
	public Lote editaData(Lote lote, String novaData) {
		lote.setData(novaData);
		return lote;
	}

	/**
	 * fiz isso pra pegar os lotes pelo id.
	 */
	
	@Override
	public Optional<Lote> getLoteById(long id) {
		return loteRepository.findById(id);
	}

	/**
	 * cadastrando data nos lotes
	 */ 
	
	public void cadastraDataAoLote(long id, String data) {
		Optional<Lote> optionaLote = loteRepository.findById(id);
		Lote lote = optionaLote.get();
		lote.setData(data);
	}

	/**
	 * exibindo data de validade de determinado lote
	 */
	
	
	public String exibirDataValidade(long id) {
		Optional<Lote> optLote = loteRepository.findById(id);
		return optLote.get().getData();
	}
}
