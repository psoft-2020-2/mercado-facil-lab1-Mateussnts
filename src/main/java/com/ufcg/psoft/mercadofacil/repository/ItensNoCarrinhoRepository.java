  
package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.ItensNoCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensNoCarrinhoRepository extends JpaRepository<ItensNoCarrinho, Long> {

}