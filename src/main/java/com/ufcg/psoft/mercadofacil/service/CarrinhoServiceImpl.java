package com.ufcg.psoft.mercadofacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.ItensNoCarrinho;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.CarrinhoRepository;
import com.ufcg.psoft.mercadofacil.repository.ItensNoCarrinhoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    
    @Autowired
    private ItensNoCarrinhoRepository carrinhoItemRepository;

    @Override
    public void criaCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinhoRepository.save(carrinho);
    }
    
    /**
     * us1 adicionando produto no carrinho
     * *que comecem os jogos*
     */
    
	@Override
    public void adicionaProduto(Long id, Produto produto, int quantidade){
		
        Optional<Carrinho> carrinhoID = carrinhoRepository.findById(id);
        Carrinho carrinho = carrinhoID.get();
        if (!carrinho.isFinalizado() && produto.isDisponivel()) {
            if (carrinho.getItemProduto(produto) != null) {
                ItensNoCarrinho item = carrinho.getItemProduto(produto);
                int qtd = item.getQuantidade() + quantidade;
                item.setQuantidade(qtd);
                carrinhoItemRepository.save(item);
                carrinhoRepository.save(carrinho);
            } else {
                ItensNoCarrinho itemSalvo = carrinho.addItemCarrinho(produto, quantidade);
                carrinhoItemRepository.save(itemSalvo);
                carrinhoRepository.save(carrinho);
            }
        }
    }

    /**
     * us2 estou deletando um item especifico do carrinho
     */
    
	@Override
    public boolean deletaItemCarrinho(Long id, Produto produto) {
        Optional<Carrinho> carrinhoID = carrinhoRepository.findById(id);

        Carrinho carrinho = carrinhoID.get();
        List<ItensNoCarrinho> listaDeItens = carrinho.getListaItensCarrinho();
        ItensNoCarrinho item = carrinho.getItemProduto(produto);
        if(item.getProduto().equals(produto)){
            listaDeItens.remove(item);
            carrinhoItemRepository.delete(item);
            carrinhoRepository.save(carrinho);
            return true;
        }
        return false;
    }

	/**
	 * us04 nao entendi muito bem mas acredito que devo deletar o carrinho
	 * por completo *somio*
	 * 
	 * na duvida vo deleta o carrinho todo e a compra
	 */
	
	public boolean deletaCarrinho(Long id) {
        Optional<Carrinho> carrinhoID = carrinhoRepository.findById(id);
        Carrinho carrinho = carrinhoID.get();
        if (carrinho.getId() == id){
            carrinhoRepository.delete(carrinho);
            return true;
        }
        return false;
    }

	/**
	 * us03 finalizando carrinho de compra.
	 */
	
	@Override
    public void finalizaCarrinho(Long id) {
        Optional<Carrinho> carrinhoID = carrinhoRepository.findById(id);

        Carrinho carrinho = carrinhoID.get();
        if(!carrinho.isFinalizado()){
            carrinho.setFinalizado(true);
            carrinhoRepository.save(carrinho);
        }
    }
	
	@Override
    public Carrinho exibeCarrinho(Long id){
       Optional<Carrinho> carrinhoID = carrinhoRepository.findById(id);
       Carrinho carrinho = carrinhoID.get();
        if(!carrinho.isFinalizado() || carrinho.isFinalizado()){
            return carrinho;
        }
        return null;
    }
}
