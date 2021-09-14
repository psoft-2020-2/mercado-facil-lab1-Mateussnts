package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<Produto> getProdutoById(long id) {
		return produtoRepository.findById(id);
	}
	
	public List<Produto> getProdutoByCodigoBarra(String codigo) {
		return produtoRepository.findByCodigoBarra(codigo);
	}
	
	public void removerProdutoCadastrado(Produto produto) {
		produtoRepository.delete(produto);
	}

	public void salvarProdutoCadastrado(Produto produto) {
		produtoRepository.save(produto);		
	}

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto criaProduto(ProdutoDTO produtoDTO) {
		Produto produto = new Produto(produtoDTO.getNome(), produtoDTO.getCodigoBarra(), produtoDTO.getFabricante(),
				produtoDTO.getPreco(), produtoDTO.getCategoria(), produtoDTO.getDescricao());
		
		produto.tornaDisponivel();
		return produto;
	}

	public Produto atualizaProduto(ProdutoDTO produtoDTO, Produto produto) {
		produto.setNome(produtoDTO.getNome());
		produto.setPreco(produtoDTO.getPreco());
		produto.setCodigoBarra(produtoDTO.getCodigoBarra());
		produto.mudaFabricante(produtoDTO.getFabricante());
		produto.mudaCategoria(produtoDTO.getCategoria());
		produto.mudaCategoria(produtoDTO.getDescricao());
		
		return produto;
	}

	/**
	 * Estou me familiarizando com o ambiente
	 * isso é uma simulaçao *cuidado
	 * @return *
	 */
	
	public void cadastrarDescricao(long id, String descricao) {
		Optional<Produto> optionaProduto = produtoRepository.findById(id);
		Produto produto = optionaProduto.get();
		produto.setDescricao(descricao);
	}


	public String exibirDescricao(long id) {
		Optional<Produto> optProduto = produtoRepository.findById(id);
		return optProduto.get().getDescricao();
	}
}
