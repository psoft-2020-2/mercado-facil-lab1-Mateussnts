package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.payments.Boleto;
import com.ufcg.psoft.mercadofacil.model.payments.Cartao;
import com.ufcg.psoft.mercadofacil.model.payments.Pagamento;
import com.ufcg.psoft.mercadofacil.model.payments.PayPal;
import com.ufcg.psoft.mercadofacil.repository.PagamentoRepository;

@Service
public class PagamentoServiceImpl implements PagamentoService{

	@Autowired
	public PagamentoRepository pagamentoRepository;
	
	@Override
    public BigDecimal calculaAcrescimoPagamento(BigDecimal valorCompra, String pagamento) {
        if(pagamento.equals("Boleto".toUpperCase())) {
		
			Pagamento boleto = new Boleto(valorCompra, new BigDecimal(0));
	        BigDecimal total = boleto.calculaAcrescimo(new BigDecimal(0), valorCompra);
	        BigDecimal totalFinal = total.add(valorCompra);
	        boleto.setValorTotal(totalFinal);
	        pagamentoRepository.save(boleto);
	        return totalFinal;
        }
	
        else if(pagamento.equals("Boleto".toUpperCase())) {
	        Pagamento paypal = new PayPal(valorCompra, new BigDecimal(0.02));
	        BigDecimal total = paypal.calculaAcrescimo(new BigDecimal(0.02),valorCompra);
	        BigDecimal totalFinal = total.add(valorCompra);
	        paypal.setValorTotal(totalFinal);
	        pagamentoRepository.save(paypal);
	        return totalFinal;
        }
    
        else if(pagamento.equals("Boleto".toUpperCase())) {  
	        Pagamento cartao = new Cartao(valorCompra, new BigDecimal(0.05));
	        BigDecimal total = cartao.calculaAcrescimo(new BigDecimal(0.05),valorCompra);
	        BigDecimal totalFinal = total.add(valorCompra);
	        cartao.setValorTotal(totalFinal);
	        pagamentoRepository.save(cartao);
	        return totalFinal;
        }
	   
        return null; 
	}
}
