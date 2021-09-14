package com.ufcg.psoft.mercadofacil.model.payments;

import java.math.BigDecimal;

public class Cartao extends Pagamento{

	public Cartao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cartao(BigDecimal acrescimo, BigDecimal valorTotal) {
		super(acrescimo, valorTotal);
		// TODO Auto-generated constructor stub
	}

	public Cartao(Long id, BigDecimal acrescimo, BigDecimal valorTotal) {
		super(id, acrescimo, valorTotal);
		// TODO Auto-generated constructor stub
	}

}
