package com.ufcg.psoft.mercadofacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.mercadofacil.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}