package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Endereco;
import org.serratec.ecommerce.domain.Produto;
import org.serratec.ecommerce.repository.EnderecoRepository;
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
@RequestMapping("/api/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> localizar() {
		List<Endereco> enderecos = enderecoRepository.findAll();
		return ResponseEntity.ok(enderecos);
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "cria uma nova categoria", notes = " criar categoria")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "categoria adicionada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public Endereco criar(@RequestBody @Valid Endereco endereco) {
		return enderecoRepository.save(endereco);

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "retorna uma categoria", notes = "categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retorna categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Endereco> buscar(@PathVariable Long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		if (endereco.isPresent()) {
			return ResponseEntity.ok(endereco.get());
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
	public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
		if (!enderecoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		endereco.setId(id);
		endereco = enderecoRepository.save(endereco);
		return ResponseEntity.ok(endereco);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "deleta uma categoria", notes = "deletar categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "categoria deletada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Endereco> deletar(@PathVariable Long id) {
		if (!enderecoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		enderecoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}