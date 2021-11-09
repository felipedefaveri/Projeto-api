package org.serratec.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Pedido;
import org.serratec.ecommerce.repository.PedidoRepository;
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
@RequestMapping("/api/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> localizar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return ResponseEntity.ok(pedidos);
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "cria uma nova categoria", notes = " criar categoria")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "categoria adicionada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public Pedido criar(@RequestBody @Valid Pedido pedido) {
		return pedidoRepository.save(pedido);

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "retorna uma categoria", notes = "categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retorna categoria"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
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
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedido.setId(id);
		pedido = pedidoRepository.save(pedido);
		return ResponseEntity.ok(pedido);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "deleta uma categoria", notes = "deletar categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "categoria deletada"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "vc nao tem permissao para acessar o produto"),
			@ApiResponse(code = 404, message = "recurso nao encontrado"),
			@ApiResponse(code = 505, message = "quando ocorre uma exceção") })
	public ResponseEntity<Pedido> deletar(@PathVariable Long id) {
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedidoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}