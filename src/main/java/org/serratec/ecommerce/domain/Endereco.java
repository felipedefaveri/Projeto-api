package org.serratec.ecommerce.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "nome não pode ser vazio" )
	private String rua;
	@NotBlank(message = "nome não pode ser vazio" )
	private String numero;
	private String complemento;
	@NotBlank(message = "nome não pode ser vazio" )
	private String bairro;
	@NotBlank(message = "nome não pode ser vazio" )
	private String cidade;
	@NotBlank(message = "nome não pode ser vazio" )
	private String estado;
	@NotBlank(message = "nome não pode ser vazio" )
	private String pais;
	@NotBlank(message = "nome não pode ser vazio" )
	private String CEP;
	public Endereco(Long id, @NotBlank(message = "nome não pode ser vazio") String rua,
			@NotBlank(message = "nome não pode ser vazio") String numero, String complemento,
			@NotBlank(message = "nome não pode ser vazio") String bairro,
			@NotBlank(message = "nome não pode ser vazio") String cidade,
			@NotBlank(message = "nome não pode ser vazio") String estado,
			@NotBlank(message = "nome não pode ser vazio") String pais,
			@NotBlank(message = "nome não pode ser vazio") String cEP) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.CEP = cEP;
	}
	public Endereco() {
		
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
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		this.CEP = cEP;
	}
	
}
