package org.serratec.ecommerce.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.service.ClienteService;
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
	private ClienteService clienteService;
	
	@GetMapping
	@ApiOperation(value = "Retorna todas os clientes", notes = "Todos os clientes")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Clientes obtidos com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<List<Cliente>> obterTodos() {
		List<Cliente> clientes = clienteService.obterTodos();
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um cliente por ID", notes = "Cliente por ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente obtido com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Criar um cliente", notes = "Cria um cliente")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
		Cliente clienteSalvo = clienteService.criar(cliente);
		
		URI uri = null;
		try {
			uri = new URI("/api/cliente/" + clienteSalvo.getId());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(uri).body(clienteSalvo);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar um cliente", notes = "Atualiza um cliente")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = clienteService.atualizar(id, cliente);
		
		if (clienteAtualizado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(clienteAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um cliente", notes = "Deleta um cliente")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Cliente deletado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção")
	})
	public ResponseEntity<Cliente> deletar(@PathVariable Long id) {
		if (!clienteService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}