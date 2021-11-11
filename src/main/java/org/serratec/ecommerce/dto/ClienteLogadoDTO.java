package org.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.ecommerce.domain.Cliente;
import org.serratec.ecommerce.domain.Endereco;
import org.serratec.ecommerce.domain.Pedido;

public class ClienteLogadoDTO {
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private String email;
	private String cpf;
	private String senha;
	private Endereco endereco;
	private List<Pedido> pedidos;

	public ClienteLogadoDTO() {
	}

	public ClienteLogadoDTO(String nome, String sobrenome, LocalDate dataNascimento, String email, String cpf, String senha, Endereco endereco, List<Pedido> pedidos) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
	}

	public ClienteLogadoDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.dataNascimento = cliente.getDataNascimento();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.senha = cliente.getSenha();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	public List<ClienteLogadoDTO> convert (List<Cliente> clientes) {
		List<ClienteLogadoDTO> clienteLogDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			ClienteLogadoDTO clienteLogadoDTO = new ClienteLogadoDTO(cliente);
			clienteLogDTO.add(clienteLogadoDTO);	
		}
		
		return clienteLogDTO;
	}

}

	//esta classe é usada para a entrada de dados no console
	// é referenciada no ClienteService
	// não precisamos alterar o id que é fornecido pelo DB

	// os dois-pontos são um foreach: para cada cliente único que existe na List<Cliente>,
	// a classe ClienteLogadoDTO vai receber uma nova instância, vai adicionar na nova lista
	// e retornar num novo cliente logado.
