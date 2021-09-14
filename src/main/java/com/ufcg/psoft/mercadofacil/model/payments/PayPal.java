package com.ufcg.psoft.mercadofacil.model.payments;

import java.math.BigDecimal;

public class PayPal extends Pagamento{

	
	public PayPal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PayPal(BigDecimal acrescimo, BigDecimal valorTotal) {
		super(acrescimo, valorTotal);
		// TODO Auto-generated constructor stub
	}

	public PayPal(Long id, BigDecimal acrescimo, BigDecimal valorTotal) {
		super(id, acrescimo, valorTotal);
		// TODO Auto-generated constructor stub
	}
}
