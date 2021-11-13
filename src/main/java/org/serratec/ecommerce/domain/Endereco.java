package org.serratec.ecommerce.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;
	
	@NotBlank(message = "Rua não pode ser vazio" )
	private String rua;
	
	@NotBlank(message = "Número da rua não pode ser vazio" )
	private String numero;
	
	private String complemento;
	
	@NotBlank(message = "Bairro não pode ser vazio" )
	private String bairro;
	
	@NotBlank(message = "Cidade não pode ser vazio" )
	private String cidade;
	
	@NotBlank(message = "Estado não pode ser vazio" )
	@Size(max = 2, message = "Número de caracteres excedido, use a sigla para o estado, ")
	private String estado;
	
	@NotBlank(message = "País não pode ser vazio" )
	private String pais;
	
	@NotBlank(message = "CEP não pode ser vazio" )
	@Size(max=9, message = "Número de caracteres excedido")
	@Pattern(regexp="^\\d{5}[-]?\\d{3}$")
	private String cep;
	
	public Endereco() {
		
	}

	public Endereco(Long id, String rua, String numero, String complemento, String bairro, String cidade, String estado, String pais, String cep) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}
}
