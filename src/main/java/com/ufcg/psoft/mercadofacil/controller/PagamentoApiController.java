package com.ufcg.psoft.mercadofacil.controller;
import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.service.PagamentoService;
import com.ufcg.psoft.mercadofacil.service.PerfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class PagamentoApiController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private CarrinhoService carrinhoService;
    
    @Autowired
    private PerfilService perfilService;

    @Autowired
    private CompraService compraService;

    @RequestMapping(value = "/finalizarCompra/boleto", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompraBoleto(@RequestBody Long id, String pagamento, Cliente cliente, String perfil) {   	    	
        Carrinho carrinho = carrinhoService.exibeCarrinho(id);
        BigDecimal saldo = compraService.getValorCompra(carrinho, pagamento, cliente);
        BigDecimal saldoComAcrescimo = pagamentoService.calculaAcrescimoPayPal(saldo);
       
        BigDecimal saldoComDesconto = saldoComAcrescimo;
        
        if(perfil.equals("comum".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalComum(saldoComAcrescimo);
    	} else if(perfil.equals("especial".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalEspecial(saldoComAcrescimo);
    	} else if(perfil.equals("premium".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalPremium(saldoComAcrescimo);
    	}    	
        
        compraService.finalizaCompra(carrinho, pagamento, cliente);
        carrinhoService.finalizaCarrinho(id);

        return new ResponseEntity<>(saldoComDesconto, HttpStatus.OK);
    }

    @RequestMapping(value = "/finalizarCompra/paypal", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompraPayPal(@RequestBody Long id, String pagamento, Cliente cliente, String perfil) {
        Carrinho carrinho = carrinhoService.exibeCarrinho(id);
        BigDecimal saldo = compraService.getValorCompra(carrinho, pagamento, cliente);
        BigDecimal saldoComAcrescimo = pagamentoService.calculaAcrescimoPayPal(saldo);

        BigDecimal saldoComDesconto = saldoComAcrescimo;
        
        if(perfil.equals("comum".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalComum(saldoComAcrescimo);
    	} else if(perfil.equals("especial".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalEspecial(saldoComAcrescimo);
    	} else if(perfil.equals("premium".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalPremium(saldoComAcrescimo);
    	}    	
        
        compraService.finalizaCompra(carrinho, pagamento, cliente);
        carrinhoService.finalizaCarrinho(id);

        return new ResponseEntity<>(saldoComDesconto, HttpStatus.OK);
    }

    @RequestMapping(value = "/finalizarCompra/cartaoCredito", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompraCartao(@RequestBody Long id, String pagamento, Cliente cliente, String perfil) {    	
        Carrinho carrinho = carrinhoService.exibeCarrinho(id);
        BigDecimal saldo = compraService.getValorCompra(carrinho, pagamento, cliente);
        BigDecimal saldoComAcrescimo = pagamentoService.calculaAcrescimoCartao(saldo);
        
        BigDecimal saldoComDesconto = saldoComAcrescimo;
        
        if(perfil.equals("comum".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalComum(saldoComAcrescimo);
    	} else if(perfil.equals("especial".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalEspecial(saldoComAcrescimo);
    	} else if(perfil.equals("premium".toUpperCase())) {
    		saldoComDesconto = perfilService.calculaTotalPremium(saldoComAcrescimo);
    	}    	
        
        compraService.finalizaCompra(carrinho, pagamento, cliente);
        carrinhoService.finalizaCarrinho(id);

        return new ResponseEntity<>(saldoComDesconto, HttpStatus.OK);
    }
}