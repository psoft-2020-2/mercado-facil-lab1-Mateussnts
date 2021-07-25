package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Pagamento;
import com.ufcg.psoft.mercadofacil.repository.PagamentoRepository;

@Service
public class PagamentoServiceImpl implements PagamentoService{

	@Autowired
	public PagamentoRepository pagamentoRepository;
	
	@Override
    public BigDecimal calculaTotalAcrescimo(BigDecimal valorCompra) {
        Pagamento boleto = new Pagamento(valorCompra, new BigDecimal(0));
        BigDecimal total = boleto.calculaAcrescimo(new BigDecimal(0), valorCompra);
        BigDecimal totalFinal = total.add(valorCompra);
        boleto.setValorTotal(totalFinal);
        pagamentoRepository.save(boleto);
        return totalFinal;
    }
}
