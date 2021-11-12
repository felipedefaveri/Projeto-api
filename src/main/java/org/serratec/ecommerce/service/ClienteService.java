package org.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.dto.ClienteLogadoDTO;
import org.serratec.ecommerce.exception.EmailException;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<ClienteLogadoDTO> listar(){
		List<ClienteLogadoDTO> clientesDTO = new ArrayList<ClienteLogadoDTO>();
		List<Cliente> clientes = clienteRepository.findAll();
		
		for(Cliente cliente : clientes) {
			ClienteLogadoDTO clienteDTO = new ClienteLogadoDTO(cliente);
			clientesDTO.add(clienteDTO);
		}
		return clientesDTO;
  }
  
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscar(Long id) {
		return clienteRepository.findById(id);
	}

	public ClienteLogadoDTO criar(ClienteLogadoDTO clienteLogadoDTO) throws EmailException {
		
		if (clienteRepository.findByEmail(clienteLogadoDTO.getEmail()) != null) {
			throw new EmailException("E-mail existente.");
		}
		
		Cliente cliente = new Cliente();
		cliente.setNome(clienteLogadoDTO.getNome());
		cliente.setEmail(clienteLogadoDTO.getEmail());
		cliente.setSenha(passwordEncoder.encode(clienteLogadoDTO.getSenha()));
		cliente = clienteRepository.save(cliente);
		
		return new ClienteLogadoDTO(cliente);

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