package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Carrinho;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.model.Compra;
import com.ufcg.psoft.mercadofacil.repository.CompraRepository;

@Service
public class CompraServiceImpl implements CompraService{
	
	@Autowired
    private CompraRepository compraRepository;
	
	@Override
    public void criaCompra() {
        Compra compra = new Compra();
        compraRepository.save(compra);

    }
	
	/**
	 * us5 to listando compras de determinado id
	 * @param id
	 * @return
	 */
	
	@Override
    public Optional<Compra> listaCompraRealizadaId(Long id) {
        return compraRepository.findById(id);
    }
	
	/**
	 * us4 deletando compras bem a vontade 
	 */
	
	@Override
	    public boolean deletaCompra(Long id){
	        Optional<Compra> compraID = compraRepository.findById(id);
	        Compra compra = compraID.get();
            if(compra.getId().equals(id)){
                compraRepository.delete(compra);
                return true;
            }
	        
	        return false;
	    }
	
	/**
	 * us3 finalizando compra
	 * @param carrinho
	 * @param pagamento
	 * @param usuario
	 * @return
	 */
	
	@Override
    public Compra finalizaCompra(Carrinho carrinho, String pagamento, Cliente cliente) {
        Compra compra = new Compra(carrinho, pagamento, cliente);
        BigDecimal valor = compra.calculaValor();
        compra.setValor(valor);
        compraRepository.save(compra);
        return compra;
    }
	
	/**
	 * gerando descritivo de determinada compra de um determinado id
	 */
	
	@Override
	public String descritivoCompra(Long id) {
		Optional<Compra> compra = compraRepository.findById(id);
		return compra.toString();
	}
	
	@Override
    public BigDecimal getValorCompra(Carrinho carrinho, String pagamento, Cliente cliente){
        Compra compra = new Compra(carrinho, pagamento, cliente);
        return compra.calculaValor();
    }
	
}
