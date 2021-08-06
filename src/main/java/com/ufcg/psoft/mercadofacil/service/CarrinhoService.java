package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Produto;

public interface CarrinhoService {

	public void adicionaProduto(Long id, Produto produto, int quantidade);

	public boolean deletaItemCarrinho(Long id, Produto produto);

	public boolean deletaCarrinho(Long id);

	public void finalizaCarrinho(Long id);

	public void criaCarrinho();

	public Carrinho exibeCarrinho(Long id);	
}
