package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;

public interface LoteService {
	
	public List<Lote> listarLotes();

	public void salvarLote(Lote lote);
	
	public Lote criaLote(int numItens, Produto produto, String data);
	
	public Lote editaData(Lote lote, String novaData);
	
	public Optional<Lote> getLoteById(long id);
	
	public void cadastraDataAoLote(long id, String data);
	
	public String exibirDataValidade(long id);
}
