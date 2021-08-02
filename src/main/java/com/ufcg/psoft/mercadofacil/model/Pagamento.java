package com.ufcg.psoft.mercadofacil.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pagamento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected BigDecimal acrescimo;

    protected BigDecimal valorTotal;

    public Pagamento(Long id, BigDecimal acrescimo, BigDecimal valorTotal) {
        this.id = id;
        this.acrescimo = acrescimo;
        this.valorTotal= valorTotal;
    }

    public Pagamento(BigDecimal acrescimo, BigDecimal valorTotal) {
        this.acrescimo = acrescimo;
        this.valorTotal= valorTotal;

    }

    public Pagamento() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(BigDecimal acrescimo) {
        this.acrescimo = acrescimo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal calculaAcrescimo(BigDecimal porcentagemAcrescimo, BigDecimal valorCompra){
        BigDecimal valor = valorCompra.multiply(porcentagemAcrescimo);
        BigDecimal total = this.valorTotal.add(valor);
        return total;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acrescimo == null) ? 0 : acrescimo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (acrescimo == null) {
			if (other.acrescimo != null)
				return false;
		} else if (!acrescimo.equals(other.acrescimo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}
}
