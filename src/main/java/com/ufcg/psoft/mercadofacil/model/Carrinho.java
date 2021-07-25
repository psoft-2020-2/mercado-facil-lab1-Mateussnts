package com.ufcg.psoft.mercadofacil.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrinho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean finalizado;
	
	@OneToMany
	private List<ItensNoCarrinho> listaDeItens;
	
	public Carrinho(int id, boolean finalizado, List<ItensNoCarrinho> listaDeItens) {
		this.id = id;
		this.finalizado = finalizado;
		this.listaDeItens = listaDeItens;
	}
	
	public Carrinho() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	@Override
	public String toString() {
		return "Carrinho [id=" + id + ", finalizado=" + finalizado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (finalizado ? 1231 : 1237);
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrinho other = (Carrinho) obj;
		if (finalizado != other.finalizado)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	// pensei tanto que me atrapalhei
	
	public ItensNoCarrinho getItemProduto(Produto produto){
        for (ItensNoCarrinho item: listaDeItens){
            if(item.getProduto().equals(produto)){
                return item;
            }
        }

        return null;
    }

	public ItensNoCarrinho addItemCarrinho(Produto produto, int quantidade) {
		ItensNoCarrinho item = new ItensNoCarrinho(produto, quantidade);
        listaDeItens.add(item);
        return item;
    }

	public List<ItensNoCarrinho> getListaItensCarrinho() {
		return listaDeItens;
	}
}
