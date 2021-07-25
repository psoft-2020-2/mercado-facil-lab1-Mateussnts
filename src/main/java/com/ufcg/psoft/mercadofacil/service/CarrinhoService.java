package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;

public interface CarrinhoService {

	public void adicionaProduto(Produto produto, int quantidade);

	public boolean deletaItemCarrinho(Produto produto);

	public boolean deletaCarrinho(Long id);

	public void finalizaCarrinho();

	public void criaCarrinho();	
}
