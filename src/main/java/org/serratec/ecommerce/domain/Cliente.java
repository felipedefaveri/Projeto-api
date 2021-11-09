package org.serratec.ecommerce.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	
	@NotBlank(message = "nome não pode ser vazio" )
	private String nome;
	
	@NotBlank(message = "nome não pode ser vazio" )
	private String sobrenome;
	
	//TODO:Vqalidação de idade maior 18
	
	private LocalDate dataNascimento;
	
	@Email(message ="verifique o campo email")
	@NotBlank(message = "email não pode ser vazio" )
	private String email;
	
	
	
	public Cliente(Long id, @NotBlank(message = "nome não pode ser vazio") String nome,
			@NotBlank(message = "nome não pode ser vazio") String sobrenome, LocalDate dataNascimento,
			@Email(message = "verifique o campo email") String email, String cpf, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.cpf = cpf;
	//	this.endereco = endereco;
	}

	private String cpf;


	public Cliente() {
		
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
	
	/*
	 * @Embedded private Endereco endereco;
	 * 
	 * 
	 * public Endereco getEndereco() { return endereco; } public void
	 * setEndereco(Endereco endereco) { this.endereco = endereco; }
	 */
}
