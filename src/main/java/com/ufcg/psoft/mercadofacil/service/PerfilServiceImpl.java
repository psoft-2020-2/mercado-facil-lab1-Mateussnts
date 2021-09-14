package com.ufcg.psoft.mercadofacil.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.perfil.Perfil;
import com.ufcg.psoft.mercadofacil.model.perfil.UsuarioEspecial;
import com.ufcg.psoft.mercadofacil.model.perfil.UsuarioNormal;
import com.ufcg.psoft.mercadofacil.model.perfil.UsuarioPremium;
import com.ufcg.psoft.mercadofacil.repository.PerfilRepository;

@Service
public class PerfilServiceImpl implements PerfilService{
	
	@Autowired
    private PerfilRepository perfilRepository;

	@Override
    public BigDecimal calculaTotalPerfil(BigDecimal valor, String perfil) {        
            if(perfil.equals("Comum".toUpperCase())) {
				Perfil comum = new UsuarioNormal();
	        	BigDecimal total = comum.calculaDesconto(valor, new BigDecimal(0.0));
	            BigDecimal totalFinal = valor.subtract(total);
	            comum.setValorTotal(totalFinal);
	            perfilRepository.save(comum);
	            return totalFinal;
            }
            
            else if(perfil.equals("Premium".toUpperCase())) {
            	Perfil premium = new UsuarioPremium();
                BigDecimal total = premium.calculaDesconto(valor, new BigDecimal(0.10));
                BigDecimal totalFinal = valor.subtract(total);
                premium.setValorTotal(totalFinal);
                perfilRepository.save(premium);
                return totalFinal;
            }
            
            else if(perfil.equals("Especial".toUpperCase())) {
            	 Perfil especial = new UsuarioEspecial();
                 BigDecimal total = especial.calculaDesconto(valor, new BigDecimal(0.10));
                 BigDecimal totalFinal = valor.subtract(total);
                 especial.setValorTotal(totalFinal);
                 perfilRepository.save(especial);
                 return totalFinal;
            }
            
            return null;
    }

}
