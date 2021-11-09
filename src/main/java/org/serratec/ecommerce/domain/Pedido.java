package org.serratec.ecommerce.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//TODO:verificar os relacionamentos 
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime dataPedido;

	private Double valorFinal;

	private Long quantidade;

	private LocalDateTime dataEnvio;

	private LocalDateTime dataEntrega;

	//relacionamento one to many
//	private List<Produto> produtos;

	public Pedido(Long id, LocalDateTime dataPedido, Double valorFinal, Long quantidade, LocalDateTime dataEnvio,
			LocalDateTime dataEntrega) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.valorFinal = valorFinal;
		this.quantidade = quantidade;
		this.dataEnvio = dataEnvio;
		this.dataEntrega = dataEntrega;
//		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	//public List<Produto> getProdutos() {
	//	return produtos;
	//}

	//public void setProdutos(List<Produto> produtos) {
	//.produtos = produtos;
//	}
	
	//relacionamento one to one
	//private Cliente cliente;
	
}
