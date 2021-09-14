package com.ufcg.psoft.mercadofacil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.service.CarrinhoService;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarrinhoApiController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    
    @RequestMapping(value = "/criaCarrinho", method = RequestMethod.POST)
    public ResponseEntity<?> criaCarrinho(@RequestBody Long id, int quantidade, Cliente cliente) {

        Optional<Produto> optProduto = produtoRepository.findById(id);

        if (!optProduto.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("id do produto nao encontrado"),
                    HttpStatus.NOT_FOUND);
        }
        Produto produtoOpt = optProduto.get();

        if (!produtoOpt.isDisponivel()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("produto nao encontrado"),
                    HttpStatus.NOT_FOUND);
        }

        carrinhoService.criaCarrinho();
        carrinhoService.adicionaProduto(id, produtoOpt, quantidade);


        return new ResponseEntity<>(carrinhoService.exibeCarrinho(id), HttpStatus.CREATED);
    
    }
        
    @RequestMapping(value = "/addProduto", method = RequestMethod.POST)
    public ResponseEntity<?> adicionarProdutos(@RequestBody Long id, Long idCarrinho, int quantidade) {

        Optional<Produto> optProduto = produtoRepository.findById(id);

        if (!optProduto.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto nao encontrado"),
                    HttpStatus.NOT_FOUND);
        }

        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findById(idCarrinho);

        Produto produtoOpt = optProduto.get();

        if (!optionalCarrinho.isPresent()) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Carrinho nao encontrado"),
                    HttpStatus.NOT_FOUND);
        }
        if (produtoOpt.isDisponivel()) {
            System.out.println(produtoOpt.isDisponivel());
            carrinhoService.adicionaProduto(id, produtoOpt, quantidade);
        }
        return new ResponseEntity<>(carrinhoService.exibeCarrinho(id), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/itemCarrinho", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemCarrinho(@RequestBody Long id, Produto produto) {

        boolean result = carrinhoService.deletaItemCarrinho(id, produto);

        if (!result) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto nao encontrado!"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(carrinhoService.exibeCarrinho(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deletaCarrinho", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaCarrinho(@RequestBody Long id) {

        boolean result = carrinhoService.deletaCarrinho(id);

        if (!result) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Carrinho com o id  " + id + " não pode ser encontrado!"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(carrinhoService, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/finalizaCarrinho", method = RequestMethod.POST)
    public ResponseEntity<?> finalizaCarrinho(long id){
    	
    	carrinhoService.finalizaCarrinho(id);
        return new ResponseEntity<>(carrinhoService.exibeCarrinho(id), HttpStatus.OK);
    }
}
