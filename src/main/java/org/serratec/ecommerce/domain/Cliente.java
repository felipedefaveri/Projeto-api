package org.serratec.ecommerce.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	@ApiModelProperty(value = "Identificador único do cliente")
	private Long id;
	
	@NotBlank(message = "Nome não pode ser vazio" )
	@ApiModelProperty(value = "Nome do cliente", required = true)
	private String nome;
	
	@NotBlank(message = "Sobrenome não pode ser vazio" )
	@ApiModelProperty(value = "Sobrenome do cliente", required = true)
	private String sobrenome;
	
	@Past(message = "Data inválida")
    @Column(name="data_nascimento")
	@ApiModelProperty(value = "Data de Nascimento do cliente", required = true)
	private LocalDate dataNascimento;
	
	@Email(message ="E-mail incorreto")
	@NotBlank(message = "E-mail não pode ser vazio")
	@ApiModelProperty(value = "Email do cliente", required = true)
	private String email;
	
	@CPF
	@NotBlank(message = "CPF deve conter 11 números")
	@ApiModelProperty(value = "CPF do cliente", required = true)
	private String cpf;
	
	@NotBlank(message = "Senha não pode ser vazio")
	@Size(min=6, max=10, message = "Senha deve ter entre {min} e {max} números.")
	@ApiModelProperty(value = "Senha do cliente", required = true)
	private String senha;
	
	@OneToOne
	@JoinColumn(name = "id_endereco")
	@ApiModelProperty(value = "Endereço do cliente", required = true)
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente")
	@ApiModelProperty(value = "Lista de pedidos efetuados pelo cliente", required = true)
	private List<Pedido> pedidos;

	
	
	public Cliente() {
	}

	public Cliente(Long id, String nome, String sobrenome, LocalDate dataNascimento, String email, String cpf, Endereco endereco, List<Pedido> pedidos) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.cpf = cpf;
		this.endereco = endereco;
		this.pedidos = pedidos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
}
