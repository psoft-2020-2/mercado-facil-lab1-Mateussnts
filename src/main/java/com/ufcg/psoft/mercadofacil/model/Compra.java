package com.ufcg.psoft.mercadofacil.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Carrinho carrinho;

    @OneToOne
    private Cliente cliente;
    
    private String pagamento;
    private BigDecimal valor;
    private String descricao;
    private Date data;

	public Compra(Long id, Carrinho carrinho, Cliente cliente, BigDecimal valor, String pagamento, Date data) {
		this.id = id;
		this.carrinho = carrinho;
		this.cliente = cliente;
		this.data = data;
		this.valor = valor;
		this.setPagamento(pagamento);
	}

	public Compra() {
	}

	public Compra(Carrinho carrinho2, String pagamento2, Cliente cliente2) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal calculaValor() {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal setValor(BigDecimal valor2) {
	  if (!this.carrinho.isFinalizado()){
	        List<ItensNoCarrinho> itens = carrinho.getListaItensCarrinho();
	        BigDecimal valorTotal = new BigDecimal(0);
	        for (ItensNoCarrinho item: itens){
	            BigDecimal valor = item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade()));
	            valorTotal = valorTotal.add(valor);
	
	        }
	        return valorTotal;
	    }
	    return null;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", carrinho=" + carrinho + ", cliente=" + cliente + ", pagamento=" + pagamento
				+ ", valor=" + valor + ", descricao=" + descricao + ", data=" + data + "]";
	}
	
	
}
