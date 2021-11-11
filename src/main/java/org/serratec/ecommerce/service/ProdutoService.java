package org.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Produto;
import org.serratec.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> obterTodos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> buscar(Long id) {
		return produtoRepository.findById(id);
	}

	public Produto criar(@Valid Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto atualizar(Long id, @Valid Produto produto) {
		if (!produtoRepository.existsById(id)) {
			return null;
		}
		produto.setId(id);
		return produtoRepository.save(produto);
	}

	public boolean deletar(Long id) {
		if(produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return true;			
		}
		return false;
	}
}
