package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Produto;
import org.serratec.ecommerce.repository.ProdutoRepository;
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
@RequestMapping("api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> localizar() {
		List<Produto> produtos = produtoRepository.findAll();
		return ResponseEntity.ok(produtos);
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "cria uma nova categoria", notes = " criar categoria")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "categoria adicionada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public Produto criar(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "retorna uma categoria", notes = "categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retorna categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Produto> buscar(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
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
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produto.setId(id);
		produto = produtoRepository.save(produto);
		return ResponseEntity.ok(produto);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "deleta uma categoria", notes = "deletar categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "categoria deletada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Produto> deletar(@PathVariable Long id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}