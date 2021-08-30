package com.ufcg.psoft.mercadofacil.controller;
import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.service.PagamentoService;
import com.ufcg.psoft.mercadofacil.service.PerfilService;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;

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

    @RequestMapping(value = "/finalizarCompra", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompra(@RequestBody Long id, String pagamento, Cliente cliente, String perfil) {   	    	
        
    	if (!perfil.equals("Comum") && !perfil.equals("Premium") && !perfil.equals("Especial")) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Perfil de Cliente não encontrado"),
                    HttpStatus.NOT_FOUND);
        }
    	
    	if (!pagamento.equals("Boleto") && !pagamento.equals("PayPal") && !pagamento.equals("Cartao")) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Opção de pagamento não disponível!"),
                    HttpStatus.NOT_FOUND);
        }
    	
    	Carrinho carrinho = carrinhoService.exibeCarrinho(id);
        BigDecimal saldo = compraService.getValorCompra(carrinho, pagamento, cliente);
        BigDecimal saldoComAcrescimo = pagamentoService.calculaAcrescimoPagamento(saldo, pagamento);      
        BigDecimal saldoComDesconto = perfilService.calculaTotalPerfil(saldoComAcrescimo, perfil);	
        
        compraService.finalizaCompra(carrinho, pagamento, cliente);
        carrinhoService.finalizaCarrinho(id);

        return new ResponseEntity<>(saldoComDesconto, HttpStatus.OK);
    }

    
}