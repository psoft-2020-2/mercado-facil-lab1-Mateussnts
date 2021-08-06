package com.ufcg.psoft.mercadofacil.model.payments;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Boleto extends Pagamento{

	public Boleto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boleto(BigDecimal acrescimo, BigDecimal valorTotal) {
		super(acrescimo, valorTotal);
		// TODO Auto-generated constructor stub
	}

	public Boleto(Long id, BigDecimal acrescimo, BigDecimal valorTotal) {
		super(id, acrescimo, valorTotal);
		// TODO Auto-generated constructor stub
	}

	
	
}
