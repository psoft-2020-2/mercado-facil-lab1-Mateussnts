package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;

public interface PagamentoService {

	public BigDecimal calculaAcrescimoPagamento(BigDecimal valorCompra, String pagamento);

}
