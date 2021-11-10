package org.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Categoria;
import org.serratec.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> obterTodos() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> findByNome(String nome) {
		return categoriaRepository.findByNome(nome);
	}

	public Categoria criar(@Valid Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria atualizar(String nome, @Valid Categoria categoria) {
		if(!categoriaRepository.existsByNome(nome)) {
			return null;			
		}		
		Long id = categoriaRepository.findByNome(nome).get().getId();
		categoria.setId(id);
		return categoriaRepository.save(categoria);
	}
	
	public Boolean deletar(String nome) {
		if(categoriaRepository.existsByNome(nome)) {
			categoriaRepository.deleteByNome(nome);
			return true;			
		}
		return false;
	}
}