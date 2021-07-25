package com.ufcg.psoft.mercadofacil.controller;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.PagamentoService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class PagamentoApiController {

	@Autowired
	private CarrinhoService carrinhoService;
	
    @Autowired
    private PagamentoService pagamentoService;

    @RequestMapping(value = "/finalizarPagamento", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarPagamento(@RequestBody BigDecimal valor){
		pagamentoService.calculaTotalAcrescimo(valor);
		carrinhoService.finalizaCarrinho();
		
    	return new ResponseEntity<>(pagamentoService, HttpStatus.OK);
    }
}