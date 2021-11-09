package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.repository.ClienteRepository;
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
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> localizar() {
		List<Cliente> clientes = clienteRepository.findAll();
		return ResponseEntity.ok(clientes);
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "cria uma nova categoria", notes = " criar categoria")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "categoria adicionada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public Cliente criar(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);

	}

	@GetMapping("/{nome}")
	@ApiOperation(value = "retorna uma categoria", notes = "categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retorna categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Object> buscar(@PathVariable String nome) {
		Optional<Cliente> cliente = clienteRepository.findByNome(nome);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
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
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "deleta uma categoria", notes = "deletar categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "categoria deletada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Cliente> deletar(@PathVariable Long id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}