package org.serratec.ecommerce.service;

import java.util.List;

import org.serratec.ecommerce.domain.Usuario;
import org.serratec.ecommerce.exception.EmailException;
import org.serratec.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario inserir(Usuario user) throws EmailException{
		Usuario usuario = usuarioRepository.findByEmail(user.getEmail());
		if(usuario != null) {
			throw new EmailException("Email ja cadastrado");
		}
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		return usuarioRepository.save(user);
	}
}
