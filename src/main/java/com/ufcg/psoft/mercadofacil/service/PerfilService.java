package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;

public interface PerfilService {

	public BigDecimal calculaTotalComum(BigDecimal valor);

	public BigDecimal calculaTotalPremium(BigDecimal valor);

	public BigDecimal calculaTotalEspecial(BigDecimal valor);

}
