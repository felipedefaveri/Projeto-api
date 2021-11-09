package org.serratec.ecommerce.controller;

import java.net.URI;
import java.util.List;

import org.serratec.ecommerce.domain.Usuario;
import org.serratec.ecommerce.exception.EmailException;
import org.serratec.ecommerce.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> usuarios = usuarioService.findAll();
		return ResponseEntity.ok(usuarios);

	}

	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody Usuario usuario) {
		try {
			usuario = usuarioService.inserir(usuario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/(id}").buildAndExpand(usuario.getId())
					.toUri();
			return ResponseEntity.created(uri).body(usuario);

		} catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

}
