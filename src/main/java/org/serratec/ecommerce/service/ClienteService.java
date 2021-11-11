package org.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscar(Long id) {
		return clienteRepository.findById(id);
	}

	public Cliente criar(@Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(Long id, @Valid Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			return null;
		}
		cliente.setId(id);
		return clienteRepository.save(cliente);
	}

	public boolean deletar(Long id) {
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return true;			
		}
		return false;
	}
}
