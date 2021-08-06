package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;

public interface PagamentoService {

	public BigDecimal calculaAcrescimoBoleto(BigDecimal valorCompra);

	public BigDecimal calculaAcrescimoPayPal(BigDecimal valorCompra);

	public BigDecimal calculaAcrescimoCartao(BigDecimal valorCompra);

}
