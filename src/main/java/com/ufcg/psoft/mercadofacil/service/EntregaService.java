package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;

public interface EntregaService {

	public double escolherTipoEntrega(String tipoEntrega, int distancia, Carrinho carrinho);
}
