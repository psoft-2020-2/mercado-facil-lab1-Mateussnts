package com.ufcg.psoft.mercadofacil.model.perfil;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal;


    public Perfil(Long id, BigDecimal valorTotal) {
        this.id = id;
        this.valorTotal = valorTotal;
    }


    public Perfil() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal calculaDesconto(BigDecimal valorAcrescimo, BigDecimal porcentagemDesconto){
        BigDecimal desconto = valorAcrescimo.multiply(porcentagemDesconto);
        BigDecimal total = desconto.subtract(desconto);
        return total;
    }
}
