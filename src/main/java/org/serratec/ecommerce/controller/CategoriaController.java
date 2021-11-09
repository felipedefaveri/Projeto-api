package org.serratec.ecommerce.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Categoria;
import org.serratec.ecommerce.repository.CategoriaRepository;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "cria uma nova categoria", notes = " criar categoria")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "categoria adicionada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public Categoria criar(@RequestBody @Valid Categoria categoria) {
		return categoriaRepository.save(categoria);

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "retorna uma categoria", notes = "categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retorna categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "atualiza os dados da categoria", notes = "atualizar categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "categoria atualizada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoria.setId(id);
		categoria = categoriaRepository.save(categoria);
		return ResponseEntity.ok(categoria);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "deleta uma categoria", notes = "deletar categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "categoria deletada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Categoria> deletar(@PathVariable Long id) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoriaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
// TODO:implementar metodos , DELETE seguindo o arquivo readme
