package org.serratec.ecommerce.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Endereco;
import org.serratec.ecommerce.service.EnderecoService;
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
	private EnderecoService enderecoService;
	
	@GetMapping
	@ApiOperation(value = "Retorna todas os endereços", notes = "Todos os endereços")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Endereços obtidos com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<List<Endereco>> obterTodos() {
		List<Endereco> enderecos = enderecoService.obterTodos();
		return ResponseEntity.ok(enderecos);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um endereço por ID", notes = "Endereço por ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Endereço obtido com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Endereco> buscar(@PathVariable Long id) {
		Optional<Endereco> endereco = enderecoService.buscar(id);
		if (endereco.isPresent()) {
			return ResponseEntity.ok(endereco.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Criar um endereço", notes = "Cria um endereço")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Endereço criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Endereco> criar(@Valid @RequestBody Endereco endereco) {
		Endereco enderecoSalvo = enderecoService.criar(endereco);
		
		URI uri = null;
		try {
			uri = new URI("/api/endereco/" + enderecoSalvo.getId());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(uri).body(enderecoSalvo);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar um endereço", notes = "Atualiza um endereço")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Endereço atualizado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
		Endereco enderecoAtualizado = enderecoService.atualizar(id, endereco);
		
		if (enderecoAtualizado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(enderecoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um endereço", notes = "Deleta um endereço")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Endereço deletado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Endereco> deletar(@PathVariable Long id) {
		if (!enderecoService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
