package com.ufcg.psoft.mercadofacil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.CompraRepository;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.service.CompraService;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CompraApiController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CompraService compraService;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping(value = "/criaCompra", method = RequestMethod.POST)
    public ResponseEntity<?> criaCompra(@RequestBody Long id, int quantidade) {

        Optional<Produto> optProduto = produtoRepository.findById(id);

        if (!optProduto.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto nao encontrado"),
                    HttpStatus.NOT_FOUND);
        }
        Produto produtoOpt = optProduto.get();

        compraService.criaCompra();
        carrinhoService.criaCarrinho();
        carrinhoService.adicionaProduto(id, produtoOpt, quantidade);


        return new ResponseEntity<>(carrinhoService.exibeCarrinho(id), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/listaUltimasCompras/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listaUltimasComprasId(@PathVariable("id") Long id) {

        Optional<Compra> listComprasRealizadasId = compraService.listaCompraRealizadaId(id);

        if (!listComprasRealizadasId.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Não há compras registradas!"),
                    HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(listComprasRealizadasId, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deletaCompra", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaCompra(@RequestBody Long id) {

        boolean resp = compraService.deletaCompra(id);

        if (!resp) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Compra com o id não pode ser encontrado!"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(compraService.listaCompraRealizadaId(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/descritivoCompra", method = RequestMethod.GET)
    public ResponseEntity<?> descritivoCompra(@RequestBody Long id){
    	Optional<Compra> compra = compraRepository.findById(id);
    	
    	if(!compra.isPresent()) {
    		return new ResponseEntity<CustomErrorType>(new CustomErrorType("Compra nao encontrada"),
                    HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<>(compraService.descritivoCompra(id), HttpStatus.OK);
    }
   
    
}