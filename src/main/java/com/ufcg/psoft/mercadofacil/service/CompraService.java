package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Compra;

public interface CompraService {

	public void criaCompra();
	
	public Optional<Compra> listaCompraRealizadaId(Long id);

	public Compra finalizaCompra(Carrinho carrinho, String pagamento, Cliente cliente);

	public boolean deletaCompra(Long id);

	public String descritivoCompra(Long id);

	public BigDecimal getValorCompra(Carrinho carrinho, String pagamento, Cliente cliente);
	

}
