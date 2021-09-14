package com.ufcg.psoft.mercadofacil.model.deliveryStrategy;

import com.ufcg.psoft.mercadofacil.model.Carrinho;

public interface EntregaStrategy {

	public double calculaEntrega(int distancia, Carrinho carrinho);
	
}
