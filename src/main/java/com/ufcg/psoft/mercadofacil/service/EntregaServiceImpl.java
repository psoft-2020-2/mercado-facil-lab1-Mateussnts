package com.ufcg.psoft.mercadofacil.service;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.deliveryStrategy.EntregaStrategy;
import com.ufcg.psoft.mercadofacil.model.deliveryStrategy.Express;
import com.ufcg.psoft.mercadofacil.model.deliveryStrategy.Padrao;
import com.ufcg.psoft.mercadofacil.model.deliveryStrategy.Retirada;

@Service
public class EntregaServiceImpl implements EntregaService{

	private EntregaStrategy entrega;
	
	@Override
	public double escolherTipoEntrega(String tipoEntrega, int distancia, Carrinho carrinho) {
		double valorFinal = 0.0;
		
		if(tipoEntrega.equals("Padrao")) {
			entrega = new Padrao();
			valorFinal = entrega.calculaEntrega(distancia, carrinho);
		
		}else if(tipoEntrega.equals("Express")) {
			entrega = new Express();
			valorFinal = entrega.calculaEntrega(distancia, carrinho);
		
		}else if(tipoEntrega.equals("Retirada")) {
			entrega = new Retirada();
			valorFinal = entrega.calculaEntrega(distancia, carrinho);
		
		} return valorFinal;
	}
	
}
