package org.serratec.ecommerce.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.serratec.ecommerce.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.serratec.ecommerce.service.CategoriaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	@ApiOperation(value = "Retorna todas as categorias", notes = "Todas as categorias")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Categorias obtidas com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<List<Categoria>> obterTodos() {
		List<Categoria> categorias = categoriaService.obterTodos();
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping("/{nome}")
	@ApiOperation(value = "Retorna uma categoria", notes = "Categoria por nome")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Categoria obtida com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Categoria> buscar(@PathVariable String nome) {
		Optional<Categoria> categoria = categoriaService.findByNome(nome);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cria uma nova categoria", notes = "Criar categoria")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Categoria criada com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.criar(categoria);
		
		URI uri = null;
		try {
			uri = new URI("/categoria/" + categoriaSalva.getNome());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(uri).body(categoriaSalva);	
	}

	@PutMapping("/{nome}")
	@ApiOperation(value = "Atualiza os dados da categoria", notes = "Atualizar categoria")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Categoria atualizada com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Categoria> atualizar(@PathVariable String nome, @Valid @RequestBody Categoria categoria) {
		Categoria categoriaAtualizada = categoriaService.atualizar(nome, categoria);
		if (categoriaAtualizada == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(categoriaAtualizada);
	}
	
	@Transactional
	@DeleteMapping("/{nome}")
	@ApiOperation(value = "Deleta uma categoria", notes = "Deletar categoria")
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Categoria deletada com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Categoria> deletar(@PathVariable String nome) {
		if(!categoriaService.deletar(nome)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();	
	}

}